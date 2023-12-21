@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlinx.JsExport

data class CostBreakDownPresenter(
    val discount: MoneyPresenter,
    val tax: MoneyPresenter
)