package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.PaymentDto
import kost.PaymentPresenter
import kost.PaymentSummaryDto
import kost.PaymentSummaryPresenter
import krono.PureDateTimeFormatter
import krono.TimeZone
import krono.toDateTimePresenter

fun PaymentSummaryDto.toPresenter(
    tz: TimeZone,
    currency: Currency,
    dateFormatter: PureDateTimeFormatter,
    moneyFormatter: MoneyFormatter
) = PaymentSummaryPresenter(
    items = items.map { it.toPresenter(tz,currency, dateFormatter, moneyFormatter) },
    amount = amount.toPresenter(currency,moneyFormatter),
    paid = paidAmount().toPresenter(currency,moneyFormatter),
    unpaid = unpaidAmount().toPresenter(currency,moneyFormatter),
    terms = terms,
    status = status.toPresenter(tz,currency, dateFormatter, moneyFormatter)
)

fun PaymentDto.toPresenter(
    tz: TimeZone,
    currency: Currency,
    dateFormatter: PureDateTimeFormatter,
    moneyFormatter: MoneyFormatter
) = PaymentPresenter(
    uid = uid,
    details = details,
    from = from,
    on = on.toDateTimePresenter(tz, dateFormatter),
    amount = amount.toPresenter(currency, moneyFormatter),
    to = to,
    method = method.toPresenter(currency, moneyFormatter),
    status = status
)