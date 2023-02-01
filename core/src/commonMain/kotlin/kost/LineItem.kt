@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kash.Money
import kollections.List
import kollections.iListOf
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class LineItem(
    val uid: String? = null,
    val details: String,
    val identifier: String? = null,
    val quantity: Int = 1,
    val unitRate: Monetary,
    val unit: String = "each",
    val unitDiscount: Monetary = Money(0),
    override val compoundDiscount: Monetary = Money(0),
    val tax: Tax = Tax.GENERIC_ZERO,
    val ref: VendorReference = VendorReference.UNSET,
    val photos: List<String> = iListOf()
) : Calculable {
    override val costBeforeDiscount: Monetary = unitRate * quantity

    override val discount = (unitDiscount * quantity) + compoundDiscount

    /**
     * cost of a [LineItem] without discount in mind
     */
    override val taxAmount: Monetary get() = costBeforeTax * tax.rate / 100
}