@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kash.Zero
import kash.sum
import kollections.List
import kollections.iterator
import kollections.map
import kollections.buildMap
import kollections.getOrPut
import kollections.put
import kollections.values
import kotlinx.JsExport

interface ItemizedCalculable : Calculable {
    val items: List<LineItem>

    override val discount: LineItemsDiscount

    override val costBeforeDiscount get() = items.map { it.discount.costAfter }.sum()

    val itemsDiscountTotal get() = items.map { it.discount.total }.sum()

    val taxRates get() = buildMap<TaxDeprecated, Money> {
            for (item in items) {
                val prev = getOrPut(item.tax) { Zero }
                put(item.tax, prev + item.taxAmount)
            }
        }

    override val taxAmount get() = taxRates.values.sum()
}