@file:JsExport

package kost

import bee.DocumentStatus
import kash.ZeroCents
import kash.cents
import kollections.JsExport
import kost.params.PaymentParams
import krono.Instant
import krono.LocalDate

data class PaymentCaptureOutput(
    var amount: Double? = 0.0,
    var date: LocalDate? = null,
    var details: String? = null,
    var from: String? = null,
    var to: String? = null,
    var fullyPaid: Boolean? = false,
    var status: DocumentStatus? = DocumentStatus.Draft,
    var method: PaymentMethodDto? = CashDto((amount?.cents ?: ZeroCents) * 100, ZeroCents),
    var transactionId: String? = null
) {
    fun toParams() = PaymentParams(
        amount = amount ?: throw RuntimeException("payment amount can't be null"),
        date = date?.toEpochMillisAsLong()?.let { Instant(it) },
        details = details,
        from = from,
        to = to,
        status = status ?: DocumentStatus.Draft,
        method = method ?: CashDto((amount ?: 0.0).cents, ZeroCents),
        transactionId = transactionId
    )
}