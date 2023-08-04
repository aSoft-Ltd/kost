package kost

import kash.Cents
import kotlinx.serialization.Serializable

@Serializable
sealed interface TaxDto {
    val uid: String
    val name: String
    fun of(cents: Cents):Cents
}