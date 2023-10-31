@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlin.js.JsExport
import kotlinx.serialization.Serializable

@Serializable
class PriceDto(
    val buying: Cents,
    val selling: Cents
)