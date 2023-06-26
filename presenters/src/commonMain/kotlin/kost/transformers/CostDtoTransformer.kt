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
    src = this,
    before = before.toPresenter(currency, formatter),
    after = after.toPresenter(currency, formatter),
    discount = discount.toPresenter(currency, formatter),
    taxes = taxes.toPresenter(currency, formatter)
)

internal fun CostBreakDownDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = CostBreakDownPresenter(
    discount = discount.toPresenter(currency, formatter),
    tax = tax.toPresenter(currency, formatter)
)