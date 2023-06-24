@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport
import kotlin.jvm.JvmField

data class CostDto(
    @JvmField val after: CostBreakDownDto,
    @JvmField val before: CostBreakDownDto
)