package kost

import kash.ZeroCents
import kash.cents
import kollections.List
import kollections.iEmptyList
import kommerce.Offerable
import kost.params.LineItemParams

class LineItemOutput(
    val offerable: Offerable,
    var unitPrice: Double = 0.0,
    var details: String = offerable.name,
    var quantity: Double = 1.0,
    var unit: String = "each",
    var unitDiscount: Double = 0.0,
    var overallDiscount: Double = 0.0,
    var taxes: List<Tax> = iEmptyList()
) {
    val cost
        get() = run {
            val beforeDiscount = unitPrice.cents * quantity * 100
            val discountPerItem = unitDiscount.cents * 100
            val allItemsDiscount = overallDiscount.cents * 100
            val totalDiscount = allItemsDiscount + (discountPerItem * quantity)
            val afterDiscount = beforeDiscount - totalDiscount
            val txes = taxes.toTaxesDto(afterDiscount)
            val afterTaxes = afterDiscount + txes.total
            CostDto(
                before = CostBreakDownDto(discount = beforeDiscount, tax = afterDiscount),
                after = CostBreakDownDto(discount = afterDiscount, tax = afterTaxes)
            )
        }

    fun toParams() = LineItemParams(
        data = offerable,
        details = details,
        quantity = quantity,
        unit = unit,
        unitDiscount = ZeroCents,
        taxes = taxes,
        unitPrice = unitPrice.cents,
        overallDiscount = overallDiscount.cents
    )
}