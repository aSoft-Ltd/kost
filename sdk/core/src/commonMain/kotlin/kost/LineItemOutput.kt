@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import books.FinancialAccountPresenter
import kash.Currency
import kash.MoneyFormatter
import kash.ZeroCents
import kash.cents
import kash.centsBy100
import kash.sum
import kollections.MutableList
import kollections.isNotEmpty
import kollections.map
import kommerce.Offerable
import kost.params.LineItemParams
import kost.transformers.toPresenter
import kotlinx.JsExport

class LineItemOutput(
    val offerable: Offerable,
    val inventoryUid: String?,
    val line: LineItemDto?,
    val currency: Currency,
    val formatter: MoneyFormatter,
    var unitPrice: Double?,
    var details: String?,
    var quantity: Double?,
    var unit: String?,
    var unitDiscount: Double?,
    var overallDiscount: Double?,
    var taxes: MutableList<TaxPresenter>,
    var inclusiveTaxes:Boolean?,
    var account: FinancialAccountPresenter?,
) {
    private fun isinclusiveTaxes() = inclusiveTaxes ?: false

    val cost
        get() = run {
            val n = quantity ?: 1.0
            val priceTimesQty = (unitPrice?.centsBy100 ?: ZeroCents) * n
            if (isinclusiveTaxes() && taxes.isNotEmpty()) {
                val beforeAmount = taxes.map { it.src.before(priceTimesQty) }.sum()
                CostDto(
                    beforeDiscount = beforeAmount,
                    discount = ZeroCents,
                    taxes = priceTimesQty - beforeAmount
                ).toPresenter(currency, formatter)
            } else {
                val beforeDiscount = (unitPrice?.centsBy100 ?: ZeroCents) * n
                val discountPerItem = (unitDiscount?.centsBy100 ?: ZeroCents)
                val allItemsDiscount = (overallDiscount?.centsBy100 ?: ZeroCents)
                val totalDiscount = allItemsDiscount + (discountPerItem * n)
                val beforeTaxes = beforeDiscount - totalDiscount

                CostDto(
                    beforeDiscount = beforeDiscount,
                    discount = totalDiscount,
                    taxes = taxes.map { it.src.of(beforeTaxes) }.sum()
                ).toPresenter(currency, formatter)
            }
        }

    fun toParams() = LineItemParams(
        data = offerable,
        inventoryUid = inventoryUid,
        details = details ?: offerable.name,
        quantity = quantity ?: 1.0,
        unit = unit ?: "each",
        unitDiscount = 100.cents * (unitDiscount ?: 0.0),
        taxes = taxes.map { it.src },
        unitPrice = 100.cents * (unitPrice ?: 0.0),
        overallDiscount = 100.cents * (overallDiscount ?: 0.0),
        account = account?.src
    )
}