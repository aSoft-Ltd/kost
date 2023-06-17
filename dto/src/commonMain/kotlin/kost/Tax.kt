@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Tax(
    var name: String,
    var rate: Int
) {
    fun toDto(amount: Cents) = TaxDto(name, rate, amount * rate.toDouble() / 100)
}