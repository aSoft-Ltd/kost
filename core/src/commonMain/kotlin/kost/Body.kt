@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import identifier.Unique
import kash.Monetary
import kash.Money
import kash.sum
import kash.sumOf
import kollections.List
import kollections.toIList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Body(
    override val items: List<LineItem>,
    override val compoundDiscount: Monetary = Money(0)
) : ItemizedCalculable {
    @JsName("fromArray")
    constructor(vararg items: LineItem) : this(items.toIList())

    override val costBeforeDiscount = items.sumOf { it.costBeforeDiscount }

    override val discount = items.sumOf { it.discount } + compoundDiscount

    @Transient
    val taxRates = run {
        val rates = mutableMapOf<Tax, Monetary>()
        for (item in items) {
            val prev = rates.getOrPut(item.tax) { Money(0) }
            rates[item.tax] = prev + item.taxAmount
        }
        rates
    }

    override val taxAmount = taxRates.values.sum()
}