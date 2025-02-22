@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")
package kost

import kotlinx.JsExport

sealed interface PaymentCaptureGateway<T> {
    val method: PaymentMethodDto
    val gateway: PaymentGateway

    val id get(): String = gateway.name
    val title get(): String = gateway.label

    val asQR get() = this as? PaymentCaptureQRCode<T>
    val asManualCard get() = this as? PaymentCaptureManualCard<T>
}