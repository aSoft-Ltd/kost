@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport
import kotlinx.serialization.Serializable
import kollections.List

@Serializable
data class DeliverySummary(
    val entries: List<Delivery>,
    val status: DeliveryStatus
)