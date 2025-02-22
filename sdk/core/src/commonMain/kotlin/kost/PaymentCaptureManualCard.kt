@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import koncurrent.Later
import koncurrent.SuccessfulLater
import kotlinx.JsExport

class PaymentCaptureManualCard<T>(
    override val method: PaymentMethodDto,
    override val gateway: PaymentGateway,
    private val onCapture: ()->Later<PaymentCaptureOutput>
):PaymentCaptureGateway<T> {
    fun capture():Later<PaymentCaptureOutput> {
        return onCapture()
    }
}
