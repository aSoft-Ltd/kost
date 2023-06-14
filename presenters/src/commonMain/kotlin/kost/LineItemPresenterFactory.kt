@file:Suppress("NOTHING_TO_INLINE")

package kost

import kash.Currency
import kash.MoneyFormatter
import kash.toPresenter

inline fun LineItemDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = LineItemPresenter(
    uid = uid,
    data = data,
    unitPrice = unitPrice.toPresenter(currency, formatter),
    status = status,
    details = details,
    quantity = quantity,
    unit = unit,
    ref = ref,
    photos = photos,
    costBeforeDiscount = costBeforeDiscount.toPresenter(currency, formatter),
    discount = discount.toPresenter(currency, formatter),
    taxes = taxes.toPresenter(currency, formatter),
    costBeforeTax = costBeforeTax.toPresenter(currency, formatter),
    costAfterTax = costAfterTax.toPresenter(currency, formatter)
)