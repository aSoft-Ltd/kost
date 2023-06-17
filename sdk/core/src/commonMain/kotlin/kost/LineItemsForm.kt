package kost

import symphony.Form
import symphony.FormConfig
import symphony.FormInitialzer

class LineItemsForm(
    config: FormConfig,
    initializer: FormInitialzer<LineItemsOutput, LineItemsOutput>
) : Form<LineItemsFields, LineItemsOutput, LineItemsOutput>(
    heading = "Line Items Form",
    details = "Add or remove line items",
    fields = LineItemsFields(),
    config = config,
    initializer = initializer
) {
//    fun
}