@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.DocumentStatus
import kash.Cents
import kotlinx.serialization.Serializable
import krono.Instant
import kotlinx.JsExport

@Serializable
data class PaymentDto(
    val uid: String,
    val details: String,
    val from: String,
    val on: Instant,
    val amount: Cents,
    val to: String,
    val method: PaymentMethodDto,
    val status: DocumentStatus
)