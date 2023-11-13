@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.LineItemDto
import kost.LineItemPresenter

inline fun LineItemDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = LineItemPresenter(
    src = this,
    uid = uid,
    data = data,
    unitPrice = unit.price.selling.toPresenter(currency, formatter),
    status = status,
    details = details,
    quantity = quantity,
    unit = unit.measure,
    ref = ref,
    photos = photos,
    discount = discount.toPresenter(currency, formatter),
    cost = cost.toPresenter(currency, formatter),
    taxes = toTaxesPresenter(currency, formatter),
    currency = currency,
    formatter = formatter
)