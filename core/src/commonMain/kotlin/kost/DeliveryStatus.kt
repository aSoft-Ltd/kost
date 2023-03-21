@file:JsExport
package kost

import kotlin.js.JsExport
import kotlinx.serialization.Serializable

@Serializable
enum class DeliveryStatus {
    Pending,
    InTransit,
    Arrived,
    Collected,
    Canceled
}