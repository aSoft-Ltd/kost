@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kash.Zero
import kash.sum
import kollections.List
import kost.serializers.PaymentSummarySerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class PaymentSummary(
    val items: List<Payment>,
    val amount: Money,
    val terms: String? = null
) {
    val status: PaymentStatus by lazy { computeStatus() }

    fun paidAmount() = items.map { it.amount }.sum()

    fun unpaidAmount() = amount - paidAmount()

    private fun computeStatus(): PaymentStatus {
        val paid = paidAmount()
        return when {
            paid == Zero -> Unpaid(amount)
            paid >= amount -> FullyPaid(items, amount)
            else -> PartiallyPaid(items, paid, amount)
        }
    }
}