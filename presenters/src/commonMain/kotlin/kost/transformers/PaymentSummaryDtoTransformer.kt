package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kollections.map
import kost.PaymentDto
import kost.PaymentPresenter
import kost.PaymentSummaryDto
import kost.PaymentSummaryPresenter
import krono.PresenterPattern
import krono.PureDateTimeFormatter
import krono.TimeZone
import krono.toDateTimePresenter

fun PaymentSummaryDto.toPresenter(
    tz: TimeZone,
    currency: Currency,
    pattern: PresenterPattern,
    formatter: MoneyFormatter
) = PaymentSummaryPresenter(
    items = items.map { it.toPresenter(tz,currency, pattern, formatter) },
    amount = amount.toPresenter(currency,formatter),
    paid = paidAmount().toPresenter(currency,formatter),
    unpaid = unpaidAmount().toPresenter(currency,formatter),
    terms = terms,
    status = status.toPresenter(tz,currency, pattern, formatter)
)

fun PaymentDto.toPresenter(
    tz: TimeZone,
    currency: Currency,
    pattern: PresenterPattern,
    formatter: MoneyFormatter
) = PaymentPresenter(
    uid = uid,
    details = details,
    from = from,
    on = on.toDateTimePresenter(tz, pattern),
    amount = amount.toPresenter(currency, formatter),
    to = to,
    method = method.toPresenter(currency, formatter),
    status = status
)