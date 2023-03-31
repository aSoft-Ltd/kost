@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kotlin.js.JsExport
import krono.Instant

interface Header {
    val currency: Currency
    val issueDate: Instant
    val dueDate: Instant
    val vendor: Vendor
    val ref: VendorReference
}