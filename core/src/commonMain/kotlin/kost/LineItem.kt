@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import kollections.iListOf
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class LineItem(
    val uid: String,
    val details: String,
    val quantity: Int = 1,
    val unitRate: Long,
    val unit: String = "each",
    val unitDiscount: Long = 0,
    override val compoundDiscount: Long = 0,
    val tax: Tax = Tax.GENERIC_ZERO,
    val ref: VendorReference = VendorReference.UNSET,
    val photos: List<String> = iListOf()
) : Calculable {
    override val costBeforeDiscount: Long = unitRate * quantity

    override val discount = (unitDiscount * quantity) + compoundDiscount

    /**
     * cost of a [LineItem] without discount in mind
     */
    override val taxAmount: Long get() = costBeforeTax * tax.rate / 100
}