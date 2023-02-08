@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kash.Money
import kollections.List
import kollections.iListOf
import kotlinx.serialization.Serializable
import kommerce.Offerable
import kotlin.js.JsExport

@Serializable
data class LineItem(
    val uid: String? = null,
    val data: Offerable,
    val unitRate: Monetary,
    val details: String = data.name,
    val quantity: Double = 1.0,
    val unit: String = "each",
    val unitDiscount: Monetary = Money(0),
    val tax: Tax = Tax.GENERIC_ZERO,
    val ref: VendorReference = VendorReference.UNSET,
    val photos: List<String> = iListOf(),
    val compoundDiscount: Monetary = Money(0)
) : Calculable {
    override val costBeforeDiscount = unitRate * quantity

    override val discount = discountOf(costBeforeDiscount, unitDiscount, quantity, compoundDiscount)

    override val taxAmount: Monetary get() = discount.costAfter * tax.rate / 100
}