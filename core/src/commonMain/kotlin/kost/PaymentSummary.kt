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
    val status: PaymentStatus = when (val paid = paidAmount()) {
        Zero -> Unpaid(amount)
        amount -> FullyPaid(items, amount)
        else -> PartiallyPaid(items, paid, amount)
    }
    fun paidAmount() = items.sumOf { it.amount }
    fun unpaidAmount() = amount - paidAmount()
}