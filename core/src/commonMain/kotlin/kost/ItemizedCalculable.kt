@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import kotlin.js.JsExport

interface ItemizedCalculable : Calculable {
    val items: List<LineItem>


    override val costBeforeDiscount: Long get() = items.map { it.costBeforeDiscount }.sum()
}