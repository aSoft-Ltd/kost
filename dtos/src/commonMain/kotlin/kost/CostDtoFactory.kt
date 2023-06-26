package kost

import kash.Cents
import kotlin.js.JsName

@JsName("createCost")
fun CostDto(
    beforeDiscount: Cents,
    discount: Cents,
    taxes: Cents
): CostDto {
    val afterDiscount = beforeDiscount - discount
    val beforeTax = afterDiscount
    val afterTax = beforeTax + taxes
    return CostDto(
        before = CostBreakDownDto(discount = beforeDiscount, tax = beforeTax),
        after = CostBreakDownDto(discount = afterDiscount, tax = afterTax),
        discount = discount,
        taxes = taxes
    )
}