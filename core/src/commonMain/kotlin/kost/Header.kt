@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import krono.LocalDateTime
import kotlin.js.JsExport

interface Header {
    val currency: Currency
    val createdOn: LocalDateTime
    val dueOn: LocalDateTime
    val vendor: Vendor
    val ref: VendorReference
}