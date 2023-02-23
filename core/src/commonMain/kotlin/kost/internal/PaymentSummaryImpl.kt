package kost.internal

import kash.Money
import kash.Zero
import kash.sumOf
import kollections.List
import kost.FullyPaid
import kost.PartiallyPaid
import kost.Payment
import kost.PaymentStatus
import kost.PaymentSummary
import kost.Unpaid

@PublishedApi
internal data class PaymentSummaryImpl(
    override val items: List<Payment>,
    val amount: Money
) : PaymentSummary {
    override val status: PaymentStatus = when (val paid = items.sumOf { it.amount }) {
        Zero -> Unpaid(amount)
        amount -> FullyPaid(items, amount)
        else -> PartiallyPaid(items, paid, amount)
    }
}