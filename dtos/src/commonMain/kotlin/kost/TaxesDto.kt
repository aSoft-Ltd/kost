package kost

import kash.Cents
import kash.sum
import kollections.List
import kollections.map
import kost.serializer.TaxesSerializer
import kotlinx.serialization.Serializable

@Serializable(with = TaxesSerializer::class)
data class TaxesDto(
    val items: List<TaxDto>
) {
    fun total(cents: Cents): Cents = items.map { it.of(cents) }.sum()
}