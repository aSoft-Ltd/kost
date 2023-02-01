@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kash.sumOf
import kollections.List
import kotlin.js.JsExport

interface ItemizedCalculable : Calculable {
    val items: List<LineItem>

    override val costBeforeDiscount: Monetary get() = items.sumOf { it.costBeforeDiscount }
}