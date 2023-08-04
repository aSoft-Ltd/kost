package kost.params

import kotlinx.serialization.Serializable

@Serializable
sealed interface TaxParams {
    val name: String
}