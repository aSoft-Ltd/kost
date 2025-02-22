@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import koncurrent.Later
import koncurrent.SuccessfulLater
import kotlinx.JsExport
import krono.Instant

class PaymentCaptureQRCode<T>(
//    private val order:HasDynamicCost,
    val url: ()->String,
    override val method: PaymentMethodDto,
    override val gateway: PaymentGateway,
    private val onCapture: ()->Later<PaymentCaptureOutput>,
):PaymentCaptureGateway<T> {
//    val url get() = urlGetter(order.cost().after.tax.amount.asDouble, generateUUID())

    fun capture():Later<PaymentCaptureOutput> {
        return onCapture()
    }

}
