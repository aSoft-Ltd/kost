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
    val uid: String,
    val data: Offerable,
    val unitRate: Money,
    val details: String = data.name,
    val quantity: Double = 1.0,
    val unit: String = "each",
    val unitDiscount: Money = Monetary(0),
    val tax: Tax = Tax.GENERIC_ZERO,
    val ref: VendorReference = VendorReference.UNSET,
    val photos: List<String> = iListOf(),
    val compoundDiscount: Money = Monetary(0)
) : Calculable {
    override val costBeforeDiscount = unitRate * quantity

    override val discount: LineItemDiscount = discountOf(costBeforeDiscount, unitDiscount, quantity, compoundDiscount)

    override val taxAmount: Money get() = discount.costAfter * tax.rate / 100
}