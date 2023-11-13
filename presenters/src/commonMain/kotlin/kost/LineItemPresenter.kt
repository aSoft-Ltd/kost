@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import kash.Currency
import kash.MoneyFormatter
import kash.ZeroCents
import kollections.List
import kommerce.Offerable
import kost.params.LineItemParams
import kotlin.js.JsExport

data class LineItemPresenter(
    val src: LineItemDto,
    val uid: String,
    val data: Offerable,
    val unit: UnitPresenter,
    val status: TaskStatus,
    val details: String,
    val quantity: Double,
    val ref: VendorReference,
    val photos: List<String>,
    val currency: Currency,
    val formatter: MoneyFormatter,
    val discount: LineItemDiscountPresenter,
    val taxes: TaxesPresenter,
    val price: PricePresenter
) {

    fun toParams() = LineItemParams(
        data = data,
        details = details,
        quantity = quantity,
        unit = unit.measure,
        unitDiscount = ZeroCents,
        taxes = taxes.items.map { it.src },
        unitPrice = price.selling.after.discount.cents,
        overallDiscount = discount.total.cents
    )
}