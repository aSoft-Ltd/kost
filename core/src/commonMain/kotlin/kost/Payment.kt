@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.DocumentStatus
import kash.Monetary
import kotlinx.serialization.Serializable
import krono.Instant
import kotlin.js.JsExport

@Serializable
data class Payment(
    val uid: String,
    val details: String,
    val from: String,
    val on: Instant,
    val amount: Monetary,
    val to: String,
    val method: PaymentMethod,
    val status: DocumentStatus
)