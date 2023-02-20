@file:JsExport

package kost

import kash.Monetary
import kash.sumOf
import kotlin.js.JsExport

sealed interface PaymentStatus

data class Unpaid(val amount: Monetary) : PaymentStatus

data class PartiallyPaid(val payments: List<Payment>, val total: Monetary) : PaymentStatus {
    val amount get() = payments.sumOf { it.amount }
    val remaning get() = total - amount
}

data class FullyPaid(val payments: List<Payment>) : PaymentStatus {
    val amount get() = payments.sumOf { it.amount }
}