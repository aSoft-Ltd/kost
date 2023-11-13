@file:Suppress("NOTHING_TO_INLINE")

package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kash.transformers.toPresenter
import kost.CostBreakDownDto
import kost.CostBreakDownPresenter
import kost.CostDto
import kost.CostPresenter
import kost.PriceDto
import kost.PricePresenter

fun PriceDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = PricePresenter(
    src = this,
    buying = buying.toPresenter(currency, formatter),
    selling = selling.toPresenter(currency, formatter)
)