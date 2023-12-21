@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
sealed interface PaymentMethod {
    val typ: PaymentMethodType
}

@Serializable
data class Cash(val offered: Money, val change: Money) : PaymentMethod {
    override val typ = PaymentMethodType.Cash
}

@Serializable
data class Cheque(val number: String) : PaymentMethod{
    override val typ = PaymentMethodType.Cheque
}

@Serializable
data class DebitCard(val transactionId: String, val number: String?) : PaymentMethod{
    override val typ = PaymentMethodType.DebitCard
}

@Serializable
data class CreditCard(val transactionId: String, val number: String?) : PaymentMethod{
    override val typ = PaymentMethodType.CreditCard
}

@Serializable
data class BankDeposit(val transactionId: String) : PaymentMethod{
    override val typ = PaymentMethodType.BankDeposit
}

@Serializable
data class MobilePayment(val transactionId: String) : PaymentMethod{
    override val typ = PaymentMethodType.MobilePayment
}

@Serializable
data class ElectronicTransfer(val transactionId: String) : PaymentMethod{
    override val typ = PaymentMethodType.ElectronicTransfer
}