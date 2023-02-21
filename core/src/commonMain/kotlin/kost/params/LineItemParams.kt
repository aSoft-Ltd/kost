@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import kash.Money
import kash.Zero
import kollections.List
import kollections.iListOf
import kommerce.Offerable
import kost.Tax
import kost.VendorReference
import kotlinx.serialization.Serializable
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
)