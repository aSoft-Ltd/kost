@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

data class PayRequest<out T>(
    val data: T,
    val summary: LineItems
)