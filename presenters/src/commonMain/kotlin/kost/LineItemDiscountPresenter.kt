@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

sealed interface LineItemDiscountPresenter {
    val total: MoneyPresenter
}

data class PerUnitBasedDiscountPresenter(
    val unit: MoneyPresenter,
    override val total: MoneyPresenter
) : LineItemDiscountPresenter

/**
 * A discount modelling a mixture of both PerUnitBasedDiscount and OverallLineItemDiscount
 */
data class MixedDiscountPresenter(
    val unit: MoneyPresenter,
    val overall: MoneyPresenter,
    override val total: MoneyPresenter
) : LineItemDiscountPresenter