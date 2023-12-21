@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import bee.DocumentStatus
import kash.Cents
import kash.ZeroCents
import kost.CashDto
import kost.PaymentMethodDto
import kotlinx.serialization.Serializable
import krono.Instant
import kotlinx.JsExport

@Serializable
data class PaymentParams(
    val amount: Double,
    val date: Instant?,
    val details: String?,
    val from: String? = null,
    val to: String? = null,
    val status: DocumentStatus = DocumentStatus.Draft,
    val method: PaymentMethodDto = CashDto(Cents(amount * 100), ZeroCents),
    val transactionId: String? = null
)