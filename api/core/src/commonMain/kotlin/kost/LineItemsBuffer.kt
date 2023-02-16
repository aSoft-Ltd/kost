@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kost.params.LineItemParams
import kotlin.js.JsExport

interface LineItemsBuffer : RemoteBuffer<String, LineItemParams, LineItem>