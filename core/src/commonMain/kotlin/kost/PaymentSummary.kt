@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kollections.List
import kotlin.js.JsExport

interface PaymentSummary {
    val items: List<Payment>
    val status: PaymentStatus
}