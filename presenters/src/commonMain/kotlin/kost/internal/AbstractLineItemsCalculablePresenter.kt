package kost.internal

import kost.LineItemPresenter
import kost.LineItemsCalculablePresenter
import symphony.PaginationManager
import symphony.SelectionManager
import symphony.actionsOf
import symphony.columnsOf
import symphony.paged

abstract class AbstractLineItemsCalculablePresenter : LineItemsCalculablePresenter {
    override val paginator by lazy {
        PaginationManager { no, capacity -> items.paged(no, capacity) }.also { it.initialize() }
    }

    override val selector by lazy { SelectionManager(paginator) }

    override val actions by lazy { actionsOf<LineItemPresenter>() }

    override val columns = columnsOf<LineItemPresenter> {
        column("Details") { it.item.details }
        column("Unit Price") { it.item.unitPrice.toFormattedString() }
        column("Quantity") { it.item.quantity }
        column("Discount") { it.item.discount.total.toFormattedString() }
        column("Total") { it.item.cost.after.tax.toFormattedString() }
    }
}