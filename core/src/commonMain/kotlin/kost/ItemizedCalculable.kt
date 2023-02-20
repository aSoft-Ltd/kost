@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kash.Zero
import kash.sum
import kash.sumOf
import kollections.List
import kollections.toIMap
import kotlin.js.JsExport

interface ItemizedCalculable : Calculable {
    val items: List<LineItem>

    override val discount: LineItemsDiscount

    override val costBeforeDiscount get() = items.sumOf { it.discount.costAfter }

    val itemsDiscountTotal get() = items.sumOf { it.discount.total }

    val taxRates
        get() = buildMap<Tax, Money> {
            for (item in items) {
                val prev = getOrPut(item.tax) { Zero }
                put(item.tax, prev + item.taxAmount)
            }
        }.toIMap()

    override val taxAmount get() = taxRates.values.sum()
}