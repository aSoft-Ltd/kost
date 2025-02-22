@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
data class TaxAmountDto(
    override val uid: String,
    override val name: String,
    val amount: Cents,
):TaxDto {
    override fun of(cents: Cents): Cents = amount
    override fun before(cents: Cents): Cents = cents.minus(amount)

    override fun info(): String = "TaxAmount=${amount},name=${name}"
}