package kost

import kash.Money
import kash.Zero
import kash.ZeroCents
import kash.cents
import kollections.List
import kollections.iEmptyList
import kommerce.Offerable
import kost.params.LineItemParams

class LineItemOutput(
    val offerable: Offerable,
    var unitPrice: Money = Zero,
    var details: String = offerable.name,
    var quantity: Double = 1.0,
    var unit: String = "each",
    var unitDiscount: Money = Zero,
    var overallDiscount: Money = Zero,
    var taxes: List<Tax> = iEmptyList()
) {
    val cost
        get() = run {
            val beforeDiscount = unitPrice.centsAsDouble.cents * quantity
            val discountPerItem = unitDiscount.centsAsDouble.cents
            val allItemsDiscount = overallDiscount.amountAsDouble.cents
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
        unitPrice = unitPrice.centsAsDouble.cents,
        overallDiscount = overallDiscount.centsAsDouble.cents
    )
}