package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.FullyPaidDto
import kost.FullyPaidPresenter
import kost.OverPaidDto
import kost.OverPaidPresenter
import kost.PartiallyPaidDto
import kost.PartiallyPaidPresenter
import kost.PaymentStatusDto
import kost.UnpaidDto
import kost.UnpaidPresenter
import krono.PresenterPattern
import krono.TimeZone

fun PaymentStatusDto.toPresenter(
    tz: TimeZone,
    currency: Currency,
    pattern: PresenterPattern,
    formatter: MoneyFormatter,
) = when (this) {
    is UnpaidDto -> UnpaidPresenter(amount.toPresenter(currency, formatter))

    is FullyPaidDto -> FullyPaidPresenter(
        payments = payments.map { it.toPresenter(tz, currency, pattern, formatter) },
        amount = amount.toPresenter(currency, formatter)
    )

    is PartiallyPaidDto -> PartiallyPaidPresenter(
        payments = payments.map { it.toPresenter(tz, currency, pattern, formatter) },
        amount = amount.toPresenter(currency, formatter),
        remaining = amount.toPresenter(currency, formatter),
        total = total.toPresenter(currency, formatter)
    )

    is OverPaidDto -> OverPaidPresenter(
        payments = payments.map { it.toPresenter(tz, currency, pattern, formatter) },
        amount = amount.toPresenter(currency, formatter),
        surplus = surplus.toPresenter(currency, formatter),
        total = total.toPresenter(currency, formatter)
    )
}