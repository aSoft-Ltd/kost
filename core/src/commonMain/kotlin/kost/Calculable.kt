@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kotlin.js.JsExport

interface Calculable {
    val costBeforeDiscount: Money
    val taxAmount: Money
    val discount: Discount
    val costBeforeTax: Money get() = discount.costAfter
    val costAfterTax: Money get() = costBeforeTax + taxAmount
}