@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

sealed interface PaymentMethodPresenter {
    val typ: PaymentMethodType
}

data class CashPresenter(val offered: MoneyPresenter, val change: MoneyPresenter) : PaymentMethodPresenter {
    override val typ = PaymentMethodType.Cash
}

data class ChequePresenter(val number: String) : PaymentMethodPresenter {
    override val typ = PaymentMethodType.Cheque
}

data class DebitCardPresenter(val transactionId: String, val number: String?) : PaymentMethodPresenter {
    override val typ = PaymentMethodType.DebitCard
}

data class CreditCardPresenter(val transactionId: String, val number: String?) : PaymentMethodPresenter {
    override val typ = PaymentMethodType.CreditCard
}

data class BankDepositPresenter(val transactionId: String) : PaymentMethodPresenter {
    override val typ = PaymentMethodType.BankDeposit
}

data class MobilePaymentPresenter(val transactionId: String) : PaymentMethodPresenter {
    override val typ = PaymentMethodType.MobilePayment
}

data class ElectronicTransferPresenter(val transactionId: String) : PaymentMethodPresenter {
    override val typ = PaymentMethodType.ElectronicTransfer
}