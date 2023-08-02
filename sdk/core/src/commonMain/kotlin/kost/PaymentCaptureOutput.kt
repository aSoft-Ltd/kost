@file:JsExport

package kost

import bee.DocumentStatus
import kase.catching
import kash.Cents
import kash.Currency
import kash.MoneyFormatter
import kash.ZeroCents
import kash.cents
import kash.transformers.toPresenter
import kollections.JsExport
import kost.params.PaymentParams
import krono.Instant
import krono.LocalDate

data class PaymentCaptureOutput(
    val amountRequired: Double,
    val amountPaid: Double,
    val currency: Currency,
    val formatter: MoneyFormatter,
    var amount: Double? = null,
    var date: LocalDate? = null,
    var details: String? = null,
    var from: String? = null,
    var to: String? = null,
    var status: DocumentStatus? = DocumentStatus.Draft,
    var method: PaymentMethodDto? = CashDto((amount?.cents ?: ZeroCents) * 100, ZeroCents),
    var transactionId: String? = null
) {
    fun capturedAmount() = Cents((amount ?: 0.0) * 100).toPresenter(currency, formatter)

    fun toParams() = catching {
        PaymentParams(
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
}