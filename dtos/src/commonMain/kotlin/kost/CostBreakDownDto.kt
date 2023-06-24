@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmField

@Serializable
data class CostBreakDownDto(
    @JvmField val tax: Cents,
    @JvmField val discount: Cents
)
