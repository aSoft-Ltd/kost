package kost.params

import kash.Cents

fun TaxParams(
    name: String,
    rate: Int
) = TaxRateParams(name, rate)

fun TaxParams(
    name: String,
    cents: Cents
) = TaxAmountParams(name, cents)