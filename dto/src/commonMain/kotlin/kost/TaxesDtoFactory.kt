package kost

import kash.Cents
import kollections.toIList

fun Collection<Tax>.toTaxesDto(amount: Cents) = TaxesDto(map { it.toDto(amount) }.toIList())

fun TaxesDto.toTaxes() = items.map { Tax(it.name, it.rate) }