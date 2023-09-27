@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Bill(
    override val uid: String,
    override val header: BillHeader,
    override val body: Body
) : PaymentRequest