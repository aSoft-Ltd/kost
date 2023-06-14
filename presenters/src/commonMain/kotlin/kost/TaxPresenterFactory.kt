@file:Suppress("NOTHING_TO_INLINE")

package kost

import kash.Currency
import kash.MoneyFormatter
import kash.toPresenter

inline fun TaxDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = TaxPresenter(
    name = name,
    rate = rate,
    amount = cents.toPresenter(currency, formatter)
)