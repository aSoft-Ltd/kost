@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kotlinx.serialization.Serializable
import krono.LocalDate
import krono.LocalDateTime
import krono.TimeZones
import krono.Now
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@Serializable
class Header @JvmOverloads constructor(
    val sender: Sender,
    val receiver: Receiver,
    val currency: Currency,
    val createdOn: LocalDateTime = Now(TimeZones.UTC),
    val dueOn: LocalDateTime = createdOn.atEndOfMonth(),
    val vendor: Vendor = Vendor.GENERIC,
    val ref: VendorReference = VendorReference.UNSET,
)