package kost

import kost.params.PaymentParams
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch

interface PaymentApi {
    fun append(params: PaymentParams): Later<PaymentDto>
    fun refund(params: PaymentParams): Later<PaymentDto>
    fun cancel(uid: String): Later<PaymentDto>
}