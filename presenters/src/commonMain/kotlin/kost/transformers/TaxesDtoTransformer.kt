@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.TaxesDto
import kost.TaxesPresenter

inline fun TaxesDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = TaxesPresenter(
    src = this,
    items = items.map { it.toPresenter(currency, formatter) },
    total = total.toPresenter(currency, formatter)
)