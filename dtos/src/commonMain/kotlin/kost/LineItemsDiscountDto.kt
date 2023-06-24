package kost

import kash.Cents
import kotlinx.serialization.Serializable

@Serializable
sealed interface LineItemsDiscountDto {
    val total: Cents
}