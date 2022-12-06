@file:JsExport

package kost

import kotlin.js.JsExport

sealed interface PaymentStatus

data class Unpaid(val amount: Long) : PaymentStatus

data class PartiallyPaid(val payments: List<Payment>, val total: Long) : PaymentStatus {
    val amount get() = payments.sumOf { it.amount }
    val remaning get() = total - amount
}

data class FullyPaid(val payments: List<Payment>) : PaymentStatus {
    val amount get() = payments.sumOf { it.amount }
}