package kost

import kash.MoneyPresenter
import kotlinx.serialization.Serializable

@Serializable
sealed interface LineItemsDiscountPresenter {
    val total: MoneyPresenter
}