@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kash.ZeroCents
import kollections.List
import kollections.emptyList
import kollections.listOf
import kotlinx.JsExport

data class CostPresenter(
    val src: CostDto,
    val before: CostBreakDownPresenter,
    val after: CostBreakDownPresenter,
    val discount: MoneyPresenter,
    val taxes: MoneyPresenter
) {
    val summary: List<CostSummary> by lazy {
        when {
            discount.cents > ZeroCents && taxes.cents > ZeroCents -> listOf(
                CostSummary("Value", before.discount),
                CostSummary("Discount", discount),
                CostSummary("Subtotal", after.discount),
                CostSummary("Tax", taxes)
            )

            discount.cents > ZeroCents && taxes.cents <= ZeroCents -> listOf(
                CostSummary("Subtotal", before.discount),
                CostSummary("Discount", discount)
            )

            discount.cents <= ZeroCents && taxes.cents > ZeroCents -> listOf(
                CostSummary("Subtotal", before.tax),
                CostSummary("Tax", taxes)
            )

            else -> emptyList()
        }
    }
}