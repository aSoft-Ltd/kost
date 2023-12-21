@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
sealed interface PaymentMethodDto {
    val typ: PaymentMethodType
}

@Serializable
data class CashDto(val offered: Cents, val change: Cents) : PaymentMethodDto {
    override val typ = PaymentMethodType.Cash
}

@Serializable
data class ChequeDto(val number: String) : PaymentMethodDto {
    override val typ = PaymentMethodType.Cheque
}

@Serializable
data class DebitCardDto(val transactionId: String, val number: String?) : PaymentMethodDto {
    override val typ = PaymentMethodType.DebitCard
}

@Serializable
data class CreditCardDto(val transactionId: String, val number: String?) : PaymentMethodDto {
    override val typ = PaymentMethodType.CreditCard
}

@Serializable
data class BankDepositDto(val transactionId: String) : PaymentMethodDto {
    override val typ = PaymentMethodType.BankDeposit
}

@Serializable
data class MobilePaymentDto(val transactionId: String) : PaymentMethodDto {
    override val typ = PaymentMethodType.MobilePayment
}

@Serializable
data class ElectronicTransferDto(val transactionId: String) : PaymentMethodDto {
    override val typ = PaymentMethodType.ElectronicTransfer
}