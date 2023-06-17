@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kash.MoneyPresenter
import kash.ZeroCents
import kash.transformers.toPresenter
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

fun LineItemDiscountPresenter.unit() = when (this) {
    is PerUnitBasedDiscountPresenter -> unit
    is MixedDiscountPresenter -> unit
    else -> ZeroCents.toPresenter(total.currency, total.formatter)
}


fun LineItemDiscountPresenter.overall() = when (this) {
    is NoDiscountPresenter -> ZeroCents.toPresenter(total.currency, total.formatter)
    is MixedDiscountPresenter -> overall
    is OverallDiscountPresenter -> total
    is PerUnitBasedDiscountPresenter -> ZeroCents.toPresenter(total.currency, total.formatter)
}