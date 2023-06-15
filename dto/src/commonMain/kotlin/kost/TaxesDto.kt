package kost

import kash.Cents
import kash.sum
import kollections.List
import kost.serializer.TaxesSerializer
import kotlinx.serialization.Serializable

@Serializable(with = TaxesSerializer::class)
data class TaxesDto(
    val items: List<TaxDto>
) {
    val total: Cents by lazy { items.map { it.amount }.sum() }
}