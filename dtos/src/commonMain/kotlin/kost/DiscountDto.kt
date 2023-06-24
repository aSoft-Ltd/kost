package kost

import kash.Cents
import kash.ZeroCents
import kotlinx.serialization.Serializable

@Serializable
object NoDiscountDto : LineItemDiscountDto, LineItemsDiscountDto {
    override val total = ZeroCents
}

@Serializable
data class OverallDiscountDto(
    override val total: Cents
) : LineItemDiscountDto, LineItemsDiscountDto