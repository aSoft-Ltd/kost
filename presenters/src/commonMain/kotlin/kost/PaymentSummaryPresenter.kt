@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kollections.List
import kotlin.js.JsExport

data class PaymentSummaryPresenter(
    val items: List<PaymentPresenter>,
    val amount: MoneyPresenter,
    val paid: MoneyPresenter,
    val unpaid: MoneyPresenter,
    val terms: String? = null,
    val status: PaymentStatusPresenter
)