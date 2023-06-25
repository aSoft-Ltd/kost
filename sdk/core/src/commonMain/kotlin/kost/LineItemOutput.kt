@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kash.MoneyFormatter
import kash.ZeroCents
import kash.cents
import kollections.List
import kommerce.Offerable
import kost.params.LineItemParams
import kost.transformers.toPresenter
import kotlin.js.JsExport

class LineItemOutput(
    val offerable: Offerable,
    val currency: Currency,
    val formatter: MoneyFormatter,
    var unitPrice: Double,
    var details: String,
    var quantity: Double,
    var unit: String,
    var unitDiscount: Double,
    var overallDiscount: Double,
    var taxes: List<Tax>
) {

    fun cost(): CostPresenter {
        val beforeDiscount = unitPrice.cents * quantity * 100
        val discountPerItem = unitDiscount.cents * 100
        val allItemsDiscount = overallDiscount.cents * 100
        val totalDiscount = allItemsDiscount + (discountPerItem * quantity)
        val afterDiscount = beforeDiscount - totalDiscount
        val txes = taxes.toTaxesDto(afterDiscount)
        val afterTaxes = afterDiscount + txes.total
        return CostDto(
            before = CostBreakDownDto(discount = beforeDiscount, tax = afterDiscount),
            after = CostBreakDownDto(discount = afterDiscount, tax = afterTaxes)
        ).toPresenter(currency, formatter)
    }

    fun toParams() = LineItemParams(
        data = offerable,
        details = details,
        quantity = quantity,
        unit = unit,
        unitDiscount = ZeroCents,
        taxes = taxes,
        unitPrice = unitPrice.cents,
        overallDiscount = overallDiscount.cents
    )
}