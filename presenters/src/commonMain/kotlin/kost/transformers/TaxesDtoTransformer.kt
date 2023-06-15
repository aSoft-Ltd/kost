@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kost.TaxesDto
import kost.TaxesPresenter

inline fun TaxesDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = TaxesPresenter(items.map { it.toPresenter(currency, formatter) })