package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.BankDepositDto
import kost.BankDepositPresenter
import kost.CashDto
import kost.CashPresenter
import kost.ChequeDto
import kost.ChequePresenter
import kost.CreditCardDto
import kost.CreditCardPresenter
import kost.DebitCardDto
import kost.DebitCardPresenter
import kost.ElectronicTransferDto
import kost.ElectronicTransferPresenter
import kost.MobilePaymentDto
import kost.MobilePaymentPresenter
import kost.PaymentMethodDto

fun PaymentMethodDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = when (this) {
    is CashDto -> toPresenter(currency, formatter)
    is BankDepositDto -> BankDepositPresenter(transactionId)
    is ChequeDto -> ChequePresenter(number)
    is CreditCardDto -> CreditCardPresenter(transactionId, number)
    is DebitCardDto -> DebitCardPresenter(transactionId, number)
    is ElectronicTransferDto -> ElectronicTransferPresenter(transactionId)
    is MobilePaymentDto -> MobilePaymentPresenter(transactionId)
}

private fun CashDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = CashPresenter(
    offered = offered.toPresenter(currency, formatter),
    change = change.toPresenter(currency, formatter)
)