@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import symphony.ActionsManager
import symphony.ColumnsManager
import symphony.PaginationManager
import symphony.SelectionManager
import symphony.Table
import kotlin.js.JsExport

interface LineItemsCalculablePresenter : CalculablePresenter {
    val items: List<LineItemPresenter>

    val paginator: PaginationManager<LineItemPresenter>
    val selector: SelectionManager<LineItemPresenter>
    val actions: ActionsManager<LineItemPresenter>
    val columns: ColumnsManager<LineItemPresenter>
    val table: Table<LineItemPresenter>
}