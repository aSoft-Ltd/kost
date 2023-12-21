@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kollections.List
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
sealed interface PaymentStatus {
    val amount: Money

    val asUnPaid get() = this as? Unpaid
    val asPartiallyPaid get() = this as? PartiallyPaid
    val asFullPaid get() = this as? FullyPaid
}

@Serializable
data class Unpaid(
    override val amount: Money
) : PaymentStatus

@Serializable
data class PartiallyPaid(
    val payments: List<Payment>,
    override val amount: Money,
    val total: Money
) : PaymentStatus {
    val remaining = total - amount
}

@Serializable
data class FullyPaid(
    val payments: List<Payment>,
    override val amount: Money
) : PaymentStatus