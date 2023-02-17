@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import kash.Monetary
import kash.Money
import kash.Zero
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
    val unitRate: Money,
    val details: String = data.name,
    val quantity: Double = 1.0,
    val unit: String = "each",
    val unitDiscount: Money = Zero,
    val tax: Tax = Tax.GENERIC_ZERO,
    val ref: VendorReference = VendorReference.UNSET,
    val photos: List<String> = iListOf(),
    val compoundDiscount: Money = Zero
) : Calculable {
    @Transient
    override val costBeforeDiscount = unitRate * quantity

    @Transient
    override val discount: LineItemDiscount = discountOf(costBeforeDiscount, unitDiscount, quantity, compoundDiscount)

    @Transient
    override val taxAmount: Money get() = discount.costAfter * tax.rate / 100
}