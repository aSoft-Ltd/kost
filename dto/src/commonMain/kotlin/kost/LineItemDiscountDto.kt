package kost

import kash.Cents
import kash.ZeroCents
import kotlinx.serialization.Serializable

@Serializable
sealed interface LineItemDiscountDto {
    val total: Cents
}

@Serializable
data class PerUnitBasedDiscountDto(
    val unit: Cents,
    override val total: Cents
) : LineItemDiscountDto

/**
 * A discount modelling a mixture of both PerUnitBasedDiscount and OverallLineItemDiscount
 */
@Serializable
data class MixedDiscountDto(
    val unit: Cents,
    val overall: Cents,
    override val total: Cents
) : LineItemDiscountDto

internal fun LineItemDiscountDto.unit(): Cents = when (this) {
    is PerUnitBasedDiscountDto -> unit
    is MixedDiscountDto -> unit
    else -> ZeroCents
}

internal fun LineItemDiscountDto.overall(): Cents = when (this) {
    is NoDiscountDto -> ZeroCents
    is MixedDiscountDto -> overall
    is OverallDiscountDto -> total
    is PerUnitBasedDiscountDto -> ZeroCents
}