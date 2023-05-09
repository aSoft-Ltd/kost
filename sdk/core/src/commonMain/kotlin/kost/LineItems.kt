@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import cinematic.mutableLiveOf
import keep.Cacheable
import kollections.iMutableListOf
import kost.params.LineItemParams
import kotlinx.serialization.KSerializer
import symphony.CollectionScene
import kotlin.js.JsExport

class LineItems(config: Cacheable) : CollectionScene<LineItem>(config) {

    val current = mutableLiveOf(EMPTY_ITEMS)

    override val serializer: KSerializer<LineItem> = LineItem.serializer()

    override val columns = columnsOf {
        column("image") { it.item.photos.firstOrNull() ?: "" }
        column("name") { it.item.data.name }
        column("description") { it.item.details }
        column("quantity") { it.item.quantity.toString() }
        column("amount") { it.item.discount.costBefore.amountAsDouble.toString() }
        column("discount") { it.item.discount.total.amountAsDouble.toString() }
    }

    val intentions = iMutableListOf<Intention<String, LineItemParams>>()

    fun update(calc: ItemizedCalculable?) {
        current.value = calc ?: EMPTY_ITEMS
        paginator.refresh()
    }

    fun deInitialize(clearPages: Boolean) {
        current.value = EMPTY_ITEMS
        intentions.clear()
        paginator.deInitialize(clearPages)
    }

    private companion object {
        val EMPTY_ITEMS = ItemizedCalculable()
    }
}