@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kotlinx.serialization.Serializable
import krono.LocalDateTime
import krono.TimeZones
import krono.Now
import kotlin.js.JsExport

@Serializable
data class BillHeader(
    val supplier: Subject,
    override val currency: Currency,
    override val createdOn: LocalDateTime = Now(TimeZones.UTC),
    override val dueOn: LocalDateTime = createdOn.atEndOfMonth(),
    override val vendor: Vendor = Vendor.GENERIC,
    override val ref: VendorReference = VendorReference.UNSET,
) : Header