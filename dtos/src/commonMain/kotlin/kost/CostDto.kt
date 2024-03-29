@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.JsExport
import kotlin.jvm.JvmField
import kotlinx.serialization.Serializable

@Serializable
data class CostDto internal constructor(
    @JvmField val after: CostBreakDownDto,
    @JvmField val before: CostBreakDownDto,
    val discount: Cents,
    val taxes: Cents
) {
    operator fun times(value: Int) = CostDto(
        after * value, before * value, discount * value, taxes * value
    )

    operator fun div(value: Int) = CostDto(
        after / value, before / value, discount / value, taxes / value
    )
}