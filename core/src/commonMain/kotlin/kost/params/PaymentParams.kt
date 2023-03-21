@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import bee.DocumentStatus
import kash.Monetary
import kash.Zero
import kost.Cash
import kost.PaymentMethod
import kotlinx.serialization.Serializable
import krono.Instant
import kotlin.js.JsExport

@Serializable
data class PaymentParams(
    val amount: Double,
    val date: Instant,
    val details: String?,
    val from: String? = null,
    val to: String? = null,
    val status: DocumentStatus = DocumentStatus.Draft,
    val method: PaymentMethod = Cash(Monetary(amount), Zero)
)