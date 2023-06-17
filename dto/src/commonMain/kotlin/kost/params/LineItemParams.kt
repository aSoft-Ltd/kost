@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import kash.Cents
import kash.ZeroCents
import kollections.iEmptyList
import kommerce.Offerable
import kost.Tax
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class LineItemParams(
    val data: Offerable,
    val unitPrice: Cents,
    val details: String = data.name,
    val quantity: Double = 1.0,
    val unit: String = "each",
    val unitDiscount: Cents = ZeroCents,
    val taxes: List<Tax> = iEmptyList(),
    val overallDiscount: Cents = ZeroCents
)