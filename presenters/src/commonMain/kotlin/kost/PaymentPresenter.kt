@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.DocumentStatus
import kash.MoneyPresenter
import krono.DateTimePresenter
import kotlin.js.JsExport

data class PaymentPresenter(
    val uid: String,
    val details: String,
    val from: String,
    val on: DateTimePresenter,
    val amount: MoneyPresenter,
    val to: String,
    val method: PaymentMethodPresenter,
    val status: DocumentStatus
)