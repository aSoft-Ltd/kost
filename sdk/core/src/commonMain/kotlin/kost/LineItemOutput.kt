@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kash.MoneyFormatter
import kash.ZeroCents
import kash.cents
import kash.sum
import kollections.MutableList
import kommerce.Offerable
import kost.params.LineItemParams
import kost.transformers.toPresenter
import kost.transformers.toValue
import kotlin.js.JsExport

class LineItemOutput(
    val offerable: Offerable,
    val currency: Currency,
    val formatter: MoneyFormatter,
    var unitPrice: Double?,
    var details: String?,
    var quantity: Double?,
    var unit: String?,
    var unitDiscount: Double?,
    var overallDiscount: Double?,
    val taxes: MutableList<TaxPresenter>
) {

    fun cost(): CostPresenter {
        val n = quantity ?: 1.0
        val beforeDiscount = (unitPrice?.cents ?: ZeroCents) * n * 100
        val discountPerItem = (unitDiscount?.cents ?: ZeroCents) * 100
        val allItemsDiscount = (overallDiscount?.cents ?: ZeroCents) * 100
        val totalDiscount = allItemsDiscount + (discountPerItem * n)
        val beforeTaxes = beforeDiscount - totalDiscount

        return CostDto(
            beforeDiscount = beforeDiscount,
            discount = totalDiscount,
            taxes = taxes.map { it.src.of(beforeTaxes) }.sum()
        ).toPresenter(currency, formatter)
    }

    fun toParams() = LineItemParams(
        data = offerable,
        details = details ?: offerable.name,
        quantity = quantity ?: 1.0,
        unit = unit ?: "each",
        unitDiscount = ZeroCents,
        taxes = taxes.map { it.src },
        unitPrice = unitPrice?.cents ?: ZeroCents,
        overallDiscount = overallDiscount?.cents ?: ZeroCents
    )
}