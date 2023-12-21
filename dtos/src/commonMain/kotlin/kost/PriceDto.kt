@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport
import kotlinx.serialization.Serializable

@Serializable
data class PriceDto(
    val buying: CostDto,
    val selling: CostDto
)