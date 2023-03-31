@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Money
import kollections.List
import krono.LocalDate
import kotlin.js.JsExport

interface PaymentRequestFormParams {
    val customerId: String?
    val items: List<LineItem>
    val issueDate: LocalDate?
    val discount: Money?
}