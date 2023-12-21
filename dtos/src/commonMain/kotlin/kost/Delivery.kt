@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport
import kotlinx.serialization.Serializable

@Serializable
data class Delivery(
    val uid: String,
    val number: String,
    val items: List<DeliveryLineItem>,
    val status: DeliveryStatus
)