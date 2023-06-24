package kost

import kost.params.PaymentParams
import koncurrent.Later

interface PaymentApi {
    fun append(params: PaymentParams): Later<PaymentDto>
    fun refund(params: PaymentParams): Later<PaymentDto>
    fun cancel(uid: String): Later<PaymentDto>
}