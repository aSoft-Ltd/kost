@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import symphony.Fields
import symphony.list
import kotlin.js.JsExport

class LineItemsFields : Fields<LineItemsOutput>(LineItemsOutput()) {
    val items = list(output::items)
}