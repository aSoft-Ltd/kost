@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
enum class PaymentGateway(val label:String) {
    YOCO("Yoco"),
    SNAPSCAN("SnapScan"),
}
