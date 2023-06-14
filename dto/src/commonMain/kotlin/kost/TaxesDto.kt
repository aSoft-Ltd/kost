package kost

import kash.Cents
import kash.sumOf
import kollections.List
import kost.serializer.TaxesSerializer
import kotlinx.serialization.Serializable

@Serializable(with = TaxesSerializer::class)
data class TaxesDto(
    val items: List<TaxDto>
) : List<TaxDto> by items {
    val total: Cents by lazy { items.sumOf { it.cents } }
}