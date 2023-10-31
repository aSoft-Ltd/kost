@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlin.js.JsExport
import kotlinx.serialization.Serializable

@Serializable
class ProfitDto(
    val gross: Cents,
    val margin: Double,
    val markup: Double
)