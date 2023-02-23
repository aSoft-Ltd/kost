@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kotlin.js.JsExport

sealed interface PaymentStatus {
    val amount: Money
}

data class Unpaid(
    override val amount: Money
) : PaymentStatus

data class PartiallyPaid(
    val payments: List<Payment>,
    override val amount: Money,
    val total: Money
) : PaymentStatus {
    val remaning = total - amount
}

data class FullyPaid(
    val payments: List<Payment>,
    override val amount: Money
) : PaymentStatus