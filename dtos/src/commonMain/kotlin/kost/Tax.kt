@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Tax(
    var uid: String,
    var name: String,
    var rate: Int
) {
//    fun toDto(amount: Cents) = TaxRateDto(name, rate, amount * rate.toDouble() / 100)
}