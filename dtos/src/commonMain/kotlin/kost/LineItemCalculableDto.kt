@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport

interface LineItemCalculableDto : CalculableDto {
    val taxes: TaxesDto
    val discount: LineItemDiscountDto
}