@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

//import cellar.orders.DocumentStatus
//import cellar.orders.OrderChannel
import kash.Monetary
import kash.Money
import kash.Zero
import kollections.List
import kollections.iEmptyList
import kost.params.LineItemParams
//import kost.Intention
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class DeliveryParams(
    val customerId: String? = null,
//    val channel: OrderChannel? = null,
    val notes: String? = null,
//    val intentions: List<Intention<String, LineItemParams>> = iEmptyList(),
    val paid: Monetary = Zero,
    val compoundDiscount: Monetary = Zero,
    val taxAmount: Monetary = Zero,
//    val status: DocumentStatus = DocumentStatus.Draft
) {
//    val products = intentions.filter { it.params?.data?.asProduct != null }
//    val services = intentions.filter { it.params?.data?.asService != null }
}