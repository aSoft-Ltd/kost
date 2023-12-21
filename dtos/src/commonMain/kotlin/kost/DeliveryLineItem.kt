@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport
import kotlinx.serialization.Serializable

@Serializable
data class DeliveryLineItem(
    val uid: String,
    val ref: LineItemRef,
    val quantity: Double
)