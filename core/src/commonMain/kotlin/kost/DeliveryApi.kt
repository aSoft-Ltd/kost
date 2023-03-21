@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import kost.params.PaymentParams
import kotlin.js.JsExport

interface DeliveryApi {
    fun create(params: PaymentParams): Payment
    fun refund(params: PaymentParams): Payment
    fun update(uid: String, params: PaymentParams): Payment
    fun delete(uid: String): Payment
    fun deleteBulk(uids: List<String>): List<Payment>
}