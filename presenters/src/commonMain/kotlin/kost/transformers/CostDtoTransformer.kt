@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.CostBreakDownDto
import kost.CostBreakDownPresenter
import kost.CostDto
import kost.CostPresenter

fun CostDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = CostPresenter(
    before = before.toPresenter(currency, formatter),
    after = after.toPresenter(currency, formatter)
)

internal fun CostBreakDownDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = CostBreakDownPresenter(
    discount = discount.toPresenter(currency, formatter),
    tax = discount.toPresenter(currency, formatter)
)