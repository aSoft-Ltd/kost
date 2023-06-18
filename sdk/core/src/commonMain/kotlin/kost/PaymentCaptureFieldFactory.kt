package kost

import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter
import kash.ZeroCents
import kash.transformers.toPresenter
import koncurrent.Later
import lexi.Logger
import symphony.Fields
import symphony.FormConfig
import symphony.getOrCreate
import kotlin.js.JsName
import kotlin.reflect.KMutableProperty0

@JsName("optionalPayment")
fun Fields<*>.payment(
    name: KMutableProperty0<PaymentCaptureOutput?>,
    currency: Currency,
    formatter: MoneyFormatter,
    total: MoneyPresenter,
    paid: MoneyPresenter = ZeroCents.toPresenter(currency, formatter),
    unpaid: MoneyPresenter = total,
): PaymentCaptureForm = getOrCreate(name) {
    val config = FormConfig(logger = Logger(), exitOnSubmitted = false)
    PaymentCaptureForm(
        heading = "Payment Capture",
        details = "Capture payments now",
        currency, formatter, total, paid, unpaid, config
    ) {
        onCancel { }
        onSubmit { name.setAndUpdate(it); Later(it) }
    }
}

fun Fields<*>.payment(
    name: KMutableProperty0<PaymentCaptureOutput>,
    currency: Currency,
    formatter: MoneyFormatter,
    total: MoneyPresenter,
    paid: MoneyPresenter = ZeroCents.toPresenter(currency, formatter),
    unpaid: MoneyPresenter = total,
): PaymentCaptureForm = getOrCreate(name) {
    val config = FormConfig(logger = Logger(), exitOnSubmitted = false)
    PaymentCaptureForm(
        heading = "Payment Capture",
        details = "Capture payments now",
        currency, formatter, total, paid, unpaid, config
    ) {
        onCancel { }
        onSubmit { name.setAndUpdate(it); Later(it) }
    }
}