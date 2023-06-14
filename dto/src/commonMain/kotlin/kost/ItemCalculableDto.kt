@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

interface ItemCalculableDto : CalculableDto {
    val taxes: TaxesDto
    val discount: LineItemDiscountDto
}