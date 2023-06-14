package kost

import kash.Cents
import kotlinx.serialization.Serializable

@Serializable
data class TaxDto(
    val name: String,
    val rate: Int,
    val cents: Cents
)