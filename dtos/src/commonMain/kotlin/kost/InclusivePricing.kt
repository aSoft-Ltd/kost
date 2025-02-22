package kost

import kash.Cents
import kash.cents
import kollections.toList
import kollections.List
import kotlinx.serialization.Serializable

@Serializable
class InclusivePricing(
    val amount: Double,
    val including: List<TaxDto>
) {
//    val price get():Cents = TaxesDto(inclusive = true, items = including).total(amount.cents)
}