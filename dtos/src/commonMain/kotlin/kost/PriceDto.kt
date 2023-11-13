@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    val buying: CostDto,
    val selling: CostDto
)