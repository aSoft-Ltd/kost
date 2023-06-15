@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

data class CostBreakDownPresenter(
    val discount: MoneyPresenter,
    val tax: MoneyPresenter
)