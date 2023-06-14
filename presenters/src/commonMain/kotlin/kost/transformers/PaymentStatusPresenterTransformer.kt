package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.FullyPaidDto
import kost.FullyPaidPresenter
import kost.PartiallyPaidDto
import kost.PartiallyPaidPresenter
import kost.PaymentStatusDto
import kost.UnpaidDto
import kost.UnpaidPresenter
import krono.PureDateTimeFormatter
import krono.TimeZone

fun PaymentStatusDto.toPresenter(
    tz: TimeZone,
    currency: Currency,
    dateFormatter: PureDateTimeFormatter,
    moneyFormatter: MoneyFormatter,
) = when (this) {
    is UnpaidDto -> UnpaidPresenter(amount.toPresenter(currency, moneyFormatter))

    is FullyPaidDto -> FullyPaidPresenter(
        payments = payments.map { it.toPresenter(tz, currency, dateFormatter, moneyFormatter) },
        amount = amount.toPresenter(currency, moneyFormatter)
    )

    is PartiallyPaidDto -> PartiallyPaidPresenter(
        payments = payments.map { it.toPresenter(tz, currency, dateFormatter, moneyFormatter) },
        amount = amount.toPresenter(currency, moneyFormatter),
        remaining = amount.toPresenter(currency, moneyFormatter),
        total = total.toPresenter(currency, moneyFormatter)
    )
}