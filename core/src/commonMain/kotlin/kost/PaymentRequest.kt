@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

sealed interface PaymentRequest {
    val uid: String
    val header: Header
    val body: Body
}