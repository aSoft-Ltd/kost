@file:Suppress("WRONG_EXPORTED_DECLARATION")

package payments.requests

import kotlin.js.JsExport

@JsExport
sealed interface PaymentRequest {
    val uid: String
    val header: Header
    val body: Body
}