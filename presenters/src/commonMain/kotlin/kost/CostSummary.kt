@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlinx.JsExport

data class CostSummary(val label: String, val amount: MoneyPresenter)