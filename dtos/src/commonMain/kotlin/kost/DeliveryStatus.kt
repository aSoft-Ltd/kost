@file:JsExport
package kost

import kotlinx.JsExport
import kotlinx.serialization.Serializable

@Serializable
enum class DeliveryStatus {
    Pending,
    InTransit,
    Arrived,
    Collected,
    Canceled
}