@file:Suppress("NOTHING_TO_INLINE")

package kost

import kash.Money
import kollections.List
import kost.internal.PaymentSummaryImpl

inline fun PaymentSummary(
    items: List<Payment>,
    amount: Money
): PaymentSummary = PaymentSummaryImpl(items, amount)