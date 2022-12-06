@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
enum class PaymentMethod {
    Cash,
    Cheque,
    DebitCard,
    CreditCard,
    BankDeposit,
    MobilePayment,
    ElectronicTransfer
}