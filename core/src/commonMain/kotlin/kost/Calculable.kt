@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kotlin.js.JsExport

interface Calculable {
    val costBeforeDiscount: Monetary
    val taxAmount: Monetary
    val discount: Discount
    val costBeforeTax: Monetary get() = discount.costAfter
    val costAfterTax: Monetary get() = costBeforeTax + taxAmount
}