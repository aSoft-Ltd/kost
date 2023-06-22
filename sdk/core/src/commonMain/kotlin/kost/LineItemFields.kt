@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import symphony.Fields
import symphony.double
import symphony.money
import symphony.text
import kotlin.js.JsExport

class LineItemFields(
    taxes: List<Tax>,
    item: LineItemOutput
) : Fields<LineItemOutput>(item) {
    val unitPrice = money(output::unitPrice)
    val details = text(output::details)
    val unit = text(output::unit)
    val quantity = double(output::quantity)
    val unitDiscount = money(output::unitDiscount)
    val overallDiscount = money(output::overallDiscount)
    val taxes = taxes(
        name = output::taxes,
        src = taxes
    )
}