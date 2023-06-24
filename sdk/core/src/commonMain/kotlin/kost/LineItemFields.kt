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
    val unitPrice = money(
        name = output::unitPrice,
        onChange = { state.dispatch() }
    )
    val details = text(output::details)
    val unit = text(
        name = output::unit,
        onChange = { state.dispatch() }
    )
    val quantity = double(
        name = output::quantity,
        onChange = { state.dispatch() }
    )
    val unitDiscount = money(
        name = output::unitDiscount,
        onChange = { state.dispatch() }
    )
    val overallDiscount = money(
        name = output::overallDiscount,
        onChange = { state.dispatch() }
    )
    val taxes = taxes(
        name = output::taxes,
        src = taxes,
        onChange = { state.dispatch() }
    )
}