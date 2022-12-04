@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kotlin.js.JsExport

interface Header {
    val currency: Currency
    val createdOn: Instant
    val dueOn: Instant
    val vendor: Vendor
    val ref: VendorReference
}