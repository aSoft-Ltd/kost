@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlin.js.JsExport

interface CalculableDto {
    val costBeforeDiscount: Cents
    val costBeforeTax: Cents
    val costAfterTax: Cents
}