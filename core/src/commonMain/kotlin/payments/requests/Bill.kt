@file:JsExport

package payments.requests

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class Bill(
    override val uid: String,
    override val header: Header,
    override val body: Body
) : PaymentRequest