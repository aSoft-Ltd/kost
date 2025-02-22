@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")
package kost

import kotlinx.JsExport

sealed interface PaymentCaptureMode<T> {

    val asFields get() = this as? PaymentCaptureFields<T>
    val asQR get() = this as? PaymentCaptureQRCode<T>

}