@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kollections.map
import kost.LineItemDto
import kost.TaxesPresenter

inline fun LineItemDto.toTaxesPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = TaxesPresenter(
    src = taxes,
    items = taxes.items.map { it.toValue(price.selling.before.tax, currency, formatter) },
    total = price.selling.taxes.toPresenter(currency, formatter)
)