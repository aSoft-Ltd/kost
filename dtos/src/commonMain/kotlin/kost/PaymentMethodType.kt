package kost

import kotlinx.serialization.Serializable

@Serializable
enum class PaymentMethodType(val label: String) {
    Cash("Cash"),
    Cheque("Cheque"),
    DebitCard("Debit Card"),
    CreditCard("Credit card"),
    BankDeposit("Bank Deposit"),
    MobilePayment("Mobile Payment"),
    ElectronicTransfer("Electronic Transfer"),
}