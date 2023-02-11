@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import koncurrent.Later
import kotlin.js.JsExport

interface LineItemsApi {
    fun load(): Later<List<LineItem>>
}