@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import kash.Cents
import kollections.List
import kommerce.Offerable
import kost.params.LineItemParams
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class LineItemDto(
    val uid: String,
    val data: Offerable,
    val unitPrice: Cents,
    val status: TaskStatus,
    val details: String,
    val quantity: Double,
    val unit: String,
    val ref: VendorReference,
    val photos: List<String>,
    val taxes: TaxesDto,
    val discount: LineItemDiscountDto,
) {

    val cost: CostDto by lazy {
        val beforeDiscount = unitPrice * quantity
        val afterDiscount = beforeDiscount - discount.total
        CostDto(
            beforeDiscount = beforeDiscount,
            discount = discount.total,
            taxes = taxes.total(afterDiscount)
        )
    }

    fun toParams() = LineItemParams(
        data = data,
        unitPrice = unitPrice,
        details = details,
        quantity = quantity,
        unit = unit,
        unitDiscount = discount.unit(),
        taxes = taxes.items,
        overallDiscount = discount.overall()
    )
}