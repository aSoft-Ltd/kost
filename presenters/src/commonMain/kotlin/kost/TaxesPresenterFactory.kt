@file:Suppress("NOTHING_TO_INLINE")

package kost

import kash.Currency
import kash.MoneyFormatter

inline fun TaxesDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = TaxesPresenter(items.map { it.toPresenter(currency, formatter) })