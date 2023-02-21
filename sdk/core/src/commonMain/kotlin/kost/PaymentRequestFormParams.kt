@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kollections.List
import kotlin.js.JsExport

interface PaymentRequestFormParams {
    val customerId: String?
    val items: List<LineItem>
    val discount: Money?
}