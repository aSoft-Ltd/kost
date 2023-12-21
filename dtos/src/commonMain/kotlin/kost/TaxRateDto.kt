@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
data class TaxRateDto(
    override val uid: String,
    override val name: String,
    val rate: Int,
):TaxDto {
    override fun of(cents: Cents): Cents = cents * rate / 100
}