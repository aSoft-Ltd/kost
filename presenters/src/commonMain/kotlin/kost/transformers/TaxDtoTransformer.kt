@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Cents
import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.TaxAmountDto
import kost.TaxAmountPresenter
import kost.TaxDto
import kost.TaxRateDto
import kost.TaxRatePresenter

inline fun TaxDto.toPresenter(
    cents: Cents,
    currency: Currency,
    formatter: MoneyFormatter
) = when (this) {
    is TaxAmountDto -> TaxAmountPresenter(
        src = this,
        name = name,
        total = of(cents).toPresenter(currency, formatter)
    )

    is TaxRateDto -> TaxRatePresenter(
        src = this,
        name = name,
        rate = rate,
        total = of(cents).toPresenter(currency, formatter)
    )
}