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
    item: LineItemOutput,
    onChange: ((LineItemOutput) -> Unit)?
) : Fields<LineItemOutput>(item) {

    val unitPrice = money(
        name = output::unitPrice,
        label = "Price",
        onChange = {
            onChange?.invoke(output)
            state.value = state.value.copy(output = output)
        }
    )

    val details = text(output::details)

    val unit = text(output::unit)

    val quantity = double(
        name = output::quantity,
        onChange = {
            onChange?.invoke(output)
            state.value = state.value.copy(output = output)
        }
    )

    val unitDiscount = money(
        name = output::unitDiscount,
        label = "Discount per item",
        onChange = {
            onChange?.invoke(output)
            state.value = state.value.copy(output = output)
        }
    )

    val overallDiscount = money(
        name = output::overallDiscount,
        onChange = {
            onChange?.invoke(output)
            state.value = state.value.copy(output = output)
        }
    )

    val taxes = taxes(
        name = output::taxes,
        src = taxes,
        onChange = {
            onChange?.invoke(output)
            state.value = state.value.copy(output = output)
        }
    )
}