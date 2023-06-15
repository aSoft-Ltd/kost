@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

data class CostPresenter(
    val before: CostBreakDownPresenter,
    val after: CostBreakDownPresenter
)