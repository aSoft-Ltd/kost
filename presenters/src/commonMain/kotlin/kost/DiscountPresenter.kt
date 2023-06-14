@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

data class NoDiscountPresenter(
    override val total: MoneyPresenter
) : LineItemDiscountPresenter, LineItemsDiscountPresenter

data class OverallDiscountPresenter(
    override val total: MoneyPresenter
) : LineItemDiscountPresenter, LineItemsDiscountPresenter