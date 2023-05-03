@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed interface PaymentMethod {
    val typ: PaymentMethodType
}

data class Cash(val offered: Money, val change: Money) : PaymentMethod {
    override val typ = PaymentMethodType.Cash
}
data class Cheque(val number: String) : PaymentMethod{
    override val typ = PaymentMethodType.Cheque
}
data class DebitCard(val transactionId: String, val number: String?) : PaymentMethod{
    override val typ = PaymentMethodType.DebitCard
}
data class CreditCard(val transactionId: String, val number: String?) : PaymentMethod{
    override val typ = PaymentMethodType.CreditCard
}
data class BankDeposit(val transactionId: String) : PaymentMethod{
    override val typ = PaymentMethodType.BankDeposit
}
data class MobilePayment(val transactionId: String) : PaymentMethod{
    override val typ = PaymentMethodType.MobilePayment
}
data class ElectronicTransfer(val transactionId: String) : PaymentMethod{
    override val typ = PaymentMethodType.ElectronicTransfer
}