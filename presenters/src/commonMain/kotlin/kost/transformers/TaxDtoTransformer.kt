@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.TaxRateDto
import kost.TaxPresenter

inline fun TaxRateDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = TaxPresenter(
    name = name,
    rate = rate,
    amount = amount.toPresenter(currency, formatter)
)