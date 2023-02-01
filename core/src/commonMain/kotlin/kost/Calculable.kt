@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kotlin.js.JsExport

interface Calculable {
    val costBeforeDiscount: Monetary

    /**
     * A general discount that is offered after all unit discounts have been considered
     */
    val compoundDiscount: Monetary
    val discount: Monetary
    val taxAmount: Monetary
    val costAfterDiscount: Monetary get() = costBeforeDiscount - discount
    val costBeforeTax: Monetary get() = costAfterDiscount
    val costAfterTax: Monetary get() = costBeforeTax + taxAmount
}