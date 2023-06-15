@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.ZeroCents
import kash.transformers.toPresenter
import kost.LineItemDiscountDto
import kost.MixedDiscountDto
import kost.MixedDiscountPresenter
import kost.NoDiscountDto
import kost.NoDiscountPresenter
import kost.OverallDiscountDto
import kost.OverallDiscountPresenter
import kost.PerUnitBasedDiscountDto
import kost.PerUnitBasedDiscountPresenter

fun LineItemDiscountDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = when (this) {
    is NoDiscountDto -> toPresenter(currency, formatter)
    is MixedDiscountDto -> toPresenter(currency, formatter)
    is OverallDiscountDto -> toPresenter(currency, formatter)
    is PerUnitBasedDiscountDto -> toPresenter(currency, formatter)
}

inline fun NoDiscountDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = NoDiscountPresenter(ZeroCents.toPresenter(currency, formatter))

inline fun MixedDiscountDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = MixedDiscountPresenter(
    unit = unit.toPresenter(currency, formatter),
    overall = overall.toPresenter(currency, formatter),
    total = total.toPresenter(currency, formatter)
)

inline fun OverallDiscountDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = OverallDiscountPresenter(total.toPresenter(currency, formatter))

inline fun PerUnitBasedDiscountDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = PerUnitBasedDiscountPresenter(
    unit = unit.toPresenter(currency, formatter),
    total = total.toPresenter(currency, formatter)
)