@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kotlinx.serialization.Serializable
import krono.Now
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@Serializable
data class InvoiceHeader @JvmOverloads constructor(
    val customer: Subject,
    override val currency: Currency,
    override val createdOn: Instant = Now(),
    override val dueOn: Instant = createdOn,
    override val vendor: Vendor = Vendor.GENERIC,
    override val ref: VendorReference = VendorReference.UNSET,
) : Header