@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

data class PriceDtoOld(
    val buying: CostDto,
    val selling: CostDto
)