package kost

import kash.MoneyPresenter
import kotlinx.serialization.Serializable

@Serializable
sealed interface LineItemDiscountPresenter {
    val total: MoneyPresenter
}

@Serializable
data class PerUnitBasedDiscountPresenter(
    val unit: MoneyPresenter,
    override val total: MoneyPresenter
) : LineItemDiscountPresenter

/**
 * A discount modelling a mixture of both PerUnitBasedDiscount and OverallLineItemDiscount
 */
@Serializable
data class MixedDiscountPresenter(
    val unit: MoneyPresenter,
    val overall: MoneyPresenter,
    override val total: MoneyPresenter
) : LineItemDiscountPresenter