@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import kotlin.js.JsExport

interface LineItemsCalculablePresenter : CalculablePresenter {
    val items: List<LineItemPresenter>
}