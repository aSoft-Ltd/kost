@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import kash.Cents
import kash.ZeroCents
import kollections.List
import kollections.iEmptyList
import kollections.iListOf
import kommerce.Offerable
import kost.TaxesDto
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
    val taxes: TaxesDto = TaxesDto(iEmptyList()),
    val photos: List<String> = iListOf(),
    val overallDiscount: Cents = ZeroCents
)