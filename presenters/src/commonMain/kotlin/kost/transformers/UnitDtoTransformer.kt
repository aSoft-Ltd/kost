package kost.transformers

import kash.Currency
import kash.MoneyFormatter
import kost.UnitDto
import kost.UnitPresenter

fun UnitDto.toPresenter(
    currency: Currency,
    formatter: MoneyFormatter
) = UnitPresenter(
    src = this,
    price = price.toPresenter(currency, formatter),
    measure = measure
)