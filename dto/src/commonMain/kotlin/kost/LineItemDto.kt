@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import kash.Cents
import kollections.List
import kommerce.Offerable
import kost.params.LineItemParams
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
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
    override val taxes: TaxesDto,
    override val discount: LineItemDiscountDto,
) : ItemCalculableDto {

    @Transient
    override val costBeforeDiscount by lazy { unitPrice * quantity }

    @Transient
    override val costBeforeTax: Cents by lazy { costBeforeDiscount - discount.total }

    @Transient
    override val costAfterTax: Cents by lazy { costBeforeTax - taxes.total }

    fun toParams() = LineItemParams(
        data = data,
        unitPrice = unitPrice,
        details = details,
        quantity = quantity,
        unit = unit,
        unitDiscount = discount.unit(),
        taxes = taxes,
        photos = photos,
        overallDiscount = discount.overall()
    )
}