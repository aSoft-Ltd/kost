@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bitframe.actor.Identified
import kollections.List
import koncurrent.Later
import kost.params.LineItemParams
import kotlin.js.JsExport
import kotlin.js.JsName

interface LineItemsApi {
    fun add(params: LineItemParams): Later<LineItem>

    @JsName("addBulk")
    fun add(params: List<LineItemParams>): Later<List<LineItem>>

    fun update(params: List<Identified<String, LineItemParams>>): Later<List<LineItem>>

    @JsName("updateBulk")
    fun update(params: Identified<String, LineItemParams>): Later<LineItem>
}