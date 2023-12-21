package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
sealed interface TaxDto {
    val uid: String
    val name: String
    fun of(cents: Cents):Cents
}