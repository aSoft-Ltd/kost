package kost

import kost.params.PaymentParams
import koncurrent.Later

interface PaymentApi {
    fun create(params: PaymentParams): Later<Payment>
    fun refund(params: PaymentParams): Later<Payment>
//    fun update(uid: String, params: PaymentParams): Payment
//    fun delete(uid: String): Payment
//    fun deleteBulk(uids: List<String>): List<Payment>
}