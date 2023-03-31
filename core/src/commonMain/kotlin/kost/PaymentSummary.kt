@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kash.Zero
import kash.sumOf
import kollections.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class PaymentSummary(
    val items: List<Payment>,
    val amount: Money,
    val terms: String? = null
) {
    val status: PaymentStatus = when (val paid = items.sumOf { it.amount }) {
        Zero -> Unpaid(amount)
        amount -> FullyPaid(items, amount)
        else -> PartiallyPaid(items, paid, amount)
    }
}