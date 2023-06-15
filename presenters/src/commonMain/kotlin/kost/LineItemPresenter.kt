@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import kash.MoneyPresenter
import kash.ZeroCents
import kollections.List
import kommerce.Offerable
import kost.params.LineItemParams
import kotlin.js.JsExport

data class LineItemPresenter(
    val uid: String,
    val data: Offerable,
    val unitPrice: MoneyPresenter,
    val status: TaskStatus,
    val details: String,
    val quantity: Double,
    val unit: String,
    val ref: VendorReference,
    val photos: List<String>,
    override val discount: LineItemDiscountPresenter,
    override val taxes: TaxesPresenter,
    override val cost: CostPresenter
) : LineItemCalculablePresenter {

    fun toParams() = LineItemParams(
        data = data,
        details = details,
        quantity = quantity,
        unit = unit,
        unitDiscount = ZeroCents,
        taxes = taxes.toDto(),
        photos = photos,
        unitPrice = unitPrice.cents,
        overallDiscount = discount.total.cents
    )
}