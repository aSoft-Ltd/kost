@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport

interface CalculableDto {
    val costWithLineItemDiscount: CostDto
}