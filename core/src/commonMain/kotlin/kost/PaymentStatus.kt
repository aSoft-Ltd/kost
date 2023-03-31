@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed interface PaymentStatus {
    val amount: Money
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