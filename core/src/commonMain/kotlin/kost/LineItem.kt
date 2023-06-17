@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import kash.Money
import kash.Zero
import kollections.List
import kollections.iListOf
import kommerce.Offerable
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsExport

@Serializable
data class LineItem(
    val uid: String,
    val data: Offerable,
    val unitRate: Money,
    val status: TaskStatus,
    val details: String = data.name,
    val quantity: Double = 1.0,
    val unit: String = "each",
    val unitDiscount: Money = Zero,
    val tax: TaxDeprecated = TaxDeprecated.GENERIC_ZERO,
    val ref: VendorReference = VendorReference.UNSET,
    val photos: List<String> = iListOf(),
    val compoundDiscount: Money = Zero
) : Calculable {

    @Transient
    override val costBeforeDiscount = unitRate * quantity

    @Transient
    override val discount: LineItemDiscount = discountOf(costBeforeDiscount, unitDiscount, quantity, compoundDiscount)

    override val taxAmount: Money get() = discount.costAfter * tax.rate / 100

//    fun toParams() = LineItemParams(
//        data = data,
//        unitRate = unitRate,
//        details = details,
//        quantity = quantity,
//        unit = unit,
//        unitDiscount = unitDiscount,
//        tax = tax,
//        ref = ref,
//        photos = photos,
//        compoundDiscount = compoundDiscount
//    )
}