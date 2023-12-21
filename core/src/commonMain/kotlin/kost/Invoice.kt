@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.serialization.Serializable
import kotlinx.JsExport
import kotlin.js.JsName

@JsName("InvoiceOld")
@Serializable
class Invoice(
    override val uid: String,
    override val header: InvoiceHeader,
    override val body: Body
) : PaymentRequest