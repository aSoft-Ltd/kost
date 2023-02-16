@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import kash.Monetary
import kollections.List
import kollections.iListOf
import kommerce.Offerable
import kost.Calculable
import kost.LineItemDiscount
import kost.Tax
import kost.VendorReference
import kost.discountOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsExport

@Serializable
data class LineItemParams(
    val data: Offerable,
    val unitRate: Monetary,
    val details: String = data.name,
    val quantity: Double = 1.0,
    val unit: String = "each",
    val unitDiscount: Monetary = Monetary(0),
    val tax: Tax = Tax.GENERIC_ZERO,
    val ref: VendorReference = VendorReference.UNSET,
    val photos: List<String> = iListOf(),
    val compoundDiscount: Monetary = Monetary(0)
) : Calculable {
    @Transient
    override val costBeforeDiscount = unitRate * quantity

    @Transient
    override val discount: LineItemDiscount = discountOf(costBeforeDiscount, unitDiscount, quantity, compoundDiscount)

    @Transient
    override val taxAmount: Monetary get() = discount.costAfter * tax.rate / 100
}