@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import kollections.toIList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Body(
    val items: List<LineItem>,
    override val compoundDiscount: Long = 0L
) : Calculable {
    @JsName("fromArray")
    constructor(vararg items: LineItem) : this(items.toIList())

    override val costBeforeDiscount: Long = items.sumOf { it.costBeforeDiscount }

    override val discount: Long = items.sumOf { it.discount } + compoundDiscount

    @Transient
    val taxRates = run {
        val rates = mutableMapOf<Tax, Long>()
        for (item in items) {
            val prev = rates.getOrPut(item.tax) { 0 }
            rates[item.tax] = prev + item.taxAmount
        }
        rates
    }

    override val taxAmount: Long get() = taxRates.values.sum()
}