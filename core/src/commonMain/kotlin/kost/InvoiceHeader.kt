@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kotlinx.serialization.Serializable
import krono.Now
import krono.Instant
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@Serializable
data class InvoiceHeader @JvmOverloads constructor(
    val customer: Subject,
    override val currency: Currency,
    val subject: String? = null,
    val number: String? = null,
    override val issueDate: Instant = Now(),
    override val dueDate: Instant = issueDate,
    override val vendor: Vendor = Vendor.GENERIC,
    override val ref: VendorReference = VendorReference.UNSET,
) : Header