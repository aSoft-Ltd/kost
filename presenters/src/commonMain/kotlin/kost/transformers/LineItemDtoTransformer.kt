@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import books.toPresenter
import kash.Currency
import kash.MoneyFormatter
import kost.LineItemDto
import kost.LineItemPresenter

inline fun LineItemDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = LineItemPresenter(
    src = this,
    uid = uid,
    data = data,
    unit = unit.toPresenter(currency, formatter),
    status = status,
    details = details,
    quantity = quantity,
    ref = ref,
    photos = photos,
    discount = discount.toPresenter(currency, formatter),
    taxes = toTaxesPresenter(currency, formatter),
    currency = currency,
    formatter = formatter,
    price = price.toPresenter(currency, formatter),
    account = account?.toPresenter()
)