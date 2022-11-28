@file:JsExport

package kost

import kash.Currency
import kotlinx.serialization.Serializable
import krono.LocalDate
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@Serializable
class Header @JvmOverloads constructor(
    val sender: Sender,
    val receiver: Receiver,
    val currency: Currency,
    val createdOn: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
    val dueOn: LocalDate = createdOn + DatePeriod(days = 30),
    val vendor: Vendor = Vendor.GENERIC,
    val ref: VendorReference = VendorReference.UNSET,
)