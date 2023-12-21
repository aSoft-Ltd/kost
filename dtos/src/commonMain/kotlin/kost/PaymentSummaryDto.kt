@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kash.ZeroCents
import kash.sum
import kollections.List
import kollections.map
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
data class PaymentSummaryDto(
    val items: List<PaymentDto>,
    val amount: Cents,
    val terms: String? = null
) {
    val status: PaymentStatusDto by lazy { computeStatus() }

    fun paidAmount() = items.map { it.amount }.sum()

    fun unpaidAmount() = amount - paidAmount()

    private fun computeStatus(): PaymentStatusDto {
        val paid = paidAmount()
        return when {
            paid == ZeroCents -> UnpaidDto(amount)
            paid == amount -> FullyPaidDto(items, amount)
            paid < amount -> PartiallyPaidDto(items, paid, amount)
            else -> OverPaidDto(items, paid, amount)
        }
    }
}