@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class TaxDto(
    val name: String,
    val rate: Int,
    val amount: Cents
)