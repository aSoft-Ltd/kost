@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

sealed interface PaymentStatusPresenter {
    val amount: MoneyPresenter

    val asUnPaid get() = this as? UnpaidPresenter
    val asPartiallyPaid get() = this as? PartiallyPaidPresenter
    val asFullPaid get() = this as? FullyPaidPresenter
    val asOverPaid get() = this as? OverPaidPresenter
}

data class UnpaidPresenter(
    override val amount: MoneyPresenter
) : PaymentStatusPresenter

data class PartiallyPaidPresenter(
    val payments: List<PaymentPresenter>,
    override val amount: MoneyPresenter,
    val remaining: MoneyPresenter,
    val total: MoneyPresenter
) : PaymentStatusPresenter

data class FullyPaidPresenter(
    val payments: List<PaymentPresenter>,
    override val amount: MoneyPresenter
) : PaymentStatusPresenter

data class OverPaidPresenter(
    val payments: List<PaymentPresenter>,
    override val amount: MoneyPresenter,
    val surplus: MoneyPresenter,
    val total: MoneyPresenter
) : PaymentStatusPresenter