@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kotlinx.serialization.Serializable
import krono.Instant
import kotlinx.JsExport

@Serializable
data class BillHeader(
    val supplier: Subject,
    override val currency: Currency,
    override val issueDate: Instant,
    override val dueDate: Instant = issueDate,
    override val vendor: Vendor = Vendor.GENERIC,
    override val ref: VendorReference = VendorReference.UNSET,
) : Header