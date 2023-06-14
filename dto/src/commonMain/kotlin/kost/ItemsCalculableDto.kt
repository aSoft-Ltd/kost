@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

interface ItemsCalculableDto : CalculableDto {
    val discount: LineItemsDiscountDto
}