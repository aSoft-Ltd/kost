@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kollections.List
import kollections.toIList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class Body(
    override val items: List<LineItem>,
    val compoundDiscount: Monetary = Monetary(0)
) : ItemizedCalculable {
    @JsName("fromArray")
    constructor(vararg items: LineItem) : this(items.toIList())

    @Transient
    override val discount = discountOf(items, compoundDiscount)
}