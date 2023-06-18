@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter
import kash.ZeroCents
import kash.transformers.toPresenter
import symphony.Form
import symphony.FormInitializer
import symphony.FormConfig
import kotlin.js.JsExport
import kost.PaymentCaptureFields as Fields
import kost.PaymentCaptureOutput as Params

class PaymentCaptureForm(
    heading: String,
    details: String,
    val currency: Currency,
    val formatter: MoneyFormatter,
    val total: MoneyPresenter,
    val paid: MoneyPresenter = ZeroCents.toPresenter(currency, formatter),
    val unpaid: MoneyPresenter = total,
    config: FormConfig,
    initializer: FormInitializer<Params, Any>,
) : Form<Fields, Params, Any>(heading, details, Fields(unpaid), config, initializer)