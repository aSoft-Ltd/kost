@file:JsExport

package kost

import kotlinx.serialization.Serializable
import krono.Instant
import kotlin.js.JsExport

@Serializable
data class Payment(
    val uid: String,
    val details: String,
    val from: String,
    val on: Instant,
    val amount: Long,
    val to: String,
    val method: PaymentMethod = PaymentMethod.Cash
)