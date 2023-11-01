package kost

import koncurrent.TODOLater
import kost.params.PaymentParams

class UnimplementedPaymentApi : PaymentApi {
    override fun append(params: PaymentParams) = TODOLater()

    override fun refund(params: PaymentParams) = TODOLater()

    override fun cancel(uid: String) = TODOLater()
}