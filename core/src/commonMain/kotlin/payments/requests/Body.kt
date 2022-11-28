package payments.requests

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsName

@Serializable
class Body(val items: List<LineItem>) : Calculable {
    @JsName("fromArray")
    constructor(vararg items: LineItem) : this(items.toInteroperableList())

    override val costBeforeDiscount: Long get() = items.sumOf { it.costBeforeDiscount }

    override val discount: Long get() = items.sumOf { it.discount }

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