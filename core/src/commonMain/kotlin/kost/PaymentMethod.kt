@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed interface PaymentMethod

data class Cash(val offered: Money, val change: Money) : PaymentMethod
data class Cheque(val number: String) : PaymentMethod
data class DebitCard(val transactionId: String, val number: String?) : PaymentMethod
data class CreditCard(val transactionId: String, val number: String?) : PaymentMethod
data class BankDeposit(val transactionId: String) : PaymentMethod
data class MobilePayment(val transactionId: String) : PaymentMethod
data class ElectronicTransfer(val transactionId: String) : PaymentMethod