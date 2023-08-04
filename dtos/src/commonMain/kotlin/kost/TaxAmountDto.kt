@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class TaxAmountDto(
    override val uid: String,
    override val name: String,
    val amount: Cents,
):TaxDto {
    override fun of(cents: Cents): Cents = amount
}