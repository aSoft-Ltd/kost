@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.ZeroCents
import kollections.List
import kollections.iEmptyList
import kollections.iListOf
import kotlin.js.JsExport

data class CostPresenter(
    val before: CostBreakDownPresenter,
    val after: CostBreakDownPresenter
) {
    val summary: List<CostSummary> by lazy {
        val discount = before.discount.cents - after.discount.cents
        val tax = before.tax.cents - after.tax.cents
        when {
            discount >= ZeroCents && tax >= ZeroCents -> iListOf(
                CostSummary("Value", before.discount),
                CostSummary("Discount", before.discount.copy(cents = discount)),
                CostSummary("Subtotal", after.discount),
                CostSummary("Tax", before.tax.copy(cents = tax))
            )

            discount >= ZeroCents && tax <= ZeroCents -> iListOf(
                CostSummary("Subtotal", before.discount),
                CostSummary("Discount", before.discount.copy(cents = discount))
            )

            discount <= ZeroCents && tax >= ZeroCents -> iListOf(
                CostSummary("Subtotal", before.tax),
                CostSummary("Tax", before.tax.copy(cents = tax))
            )

            else -> iEmptyList()
        }
    }
}