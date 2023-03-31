package kost

import kost.params.PaymentParams
import koncurrent.Later

interface PaymentApi {
    fun append(params: PaymentParams): Later<Payment>
    fun refund(params: PaymentParams): Later<Payment>
    fun cancel(uid: String): Later<Payment>
}