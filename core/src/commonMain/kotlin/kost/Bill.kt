@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class Bill(
    override val uid: String,
    override val header: BillHeader,
    override val body: Body
) : PaymentRequest