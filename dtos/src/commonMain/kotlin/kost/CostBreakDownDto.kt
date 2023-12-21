@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlinx.JsExport
import kotlin.jvm.JvmField

@Serializable
data class CostBreakDownDto(
    @JvmField val tax: Cents,
    @JvmField val discount: Cents
) {
    operator fun times(value: Int) = CostBreakDownDto(tax * value, discount * value)
    operator fun div(value: Int) = CostBreakDownDto(tax / value, discount / value)
}
