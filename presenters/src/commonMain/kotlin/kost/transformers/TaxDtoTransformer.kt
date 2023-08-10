@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Cents
import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.TaxAmountDto
import kost.TaxAmountPresenter
import kost.TaxAmountValue
import kost.TaxDto
import kost.TaxRateDto
import kost.TaxRatePresenter
import kost.TaxRateValue

inline fun TaxDto.toValue(
    cents: Cents,
    currency: Currency,
    formatter: MoneyFormatter
) = when (this) {
    is TaxAmountDto -> TaxAmountValue(
        src = this,
        name = name,
        total = of(cents).toPresenter(currency, formatter)
    )

    is TaxRateDto -> TaxRateValue(
        src = this,
        name = name,
        rate = rate,
        total = of(cents).toPresenter(currency, formatter)
    )
}

inline fun TaxDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = when (this) {
    is TaxAmountDto -> TaxAmountPresenter(
        src = this,
        uid = uid,
        name = name,
        amount = amount.toPresenter(currency, formatter)
    )

    is TaxRateDto -> TaxRatePresenter(
        src = this,
        uid = uid,
        name = name,
        rate = rate,
    )
}