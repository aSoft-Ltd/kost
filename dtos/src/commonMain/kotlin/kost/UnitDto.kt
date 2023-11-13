package kost

import kotlinx.serialization.Serializable

@Serializable
class UnitDto(
    val price: PriceDto,
    val measure: String
)