package kost

import kash.Cents
import kash.MoneyPresenter
import kash.ZeroCents
import kotlinx.serialization.Serializable

@Serializable
data class NoDiscountPresenter(
    override val total: MoneyPresenter
) : LineItemDiscountPresenter, LineItemsDiscountPresenter

@Serializable
data class OverallDiscountPresenter(
    override val total: MoneyPresenter
) : LineItemDiscountPresenter, LineItemsDiscountPresenter