@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import krono.LocalDateTime
import kotlinx.serialization.Serializable
import krono.Now
import krono.TimeZones
import kotlin.js.JsExport

interface Header {
    val currency: Currency
    val createdOn: LocalDateTime
    val dueOn: LocalDateTime
    val vendor: Vendor
    val ref: VendorReference
}