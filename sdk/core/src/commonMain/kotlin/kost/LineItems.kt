@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bitframe.CollectionsViewModel
import koncurrent.Later
import kotlinx.serialization.KSerializer
import live.mutableLiveOf
import presenters.collections.PageLoader
import viewmodel.ScopeConfig
import kotlin.js.JsExport

class LineItems(config: ScopeConfig<*>) : CollectionsViewModel<LineItem>(config) {

    val current = mutableLiveOf(EMPTY_ITEMS)

    override val loader = PageLoader { _, _ -> Later(current.value.items) }

    override val serializer: KSerializer<LineItem> = LineItem.serializer()

    override val columns = columnsOf {
        column("image") { it.item.photos.firstOrNull() ?: "" }
        column("name") { it.item.data.name }
        column("description") { it.item.details }
        column("quantity") { it.item.quantity.toString() }
        column("amount") { it.item.discount.costBefore.amountAsDouble.toString() }
        column("discount") { it.item.discount.total.amountAsDouble.toString() }
    }

    fun update(calc: ItemizedCalculable?) {
        current.value = calc ?: EMPTY_ITEMS
        paginator.refresh()
    }

    override fun deInitialize(clearPages: Boolean) {
        current.value = EMPTY_ITEMS
        super.deInitialize(clearPages)
    }

    private companion object {
        val EMPTY_ITEMS = ItemizedCalculable()
    }
}