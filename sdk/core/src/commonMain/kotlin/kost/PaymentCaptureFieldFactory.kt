package kost

import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter
import neat.ValidationFactory
import symphony.Changer
import symphony.Fields
import symphony.Visibility
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0

fun <P> Fields<*>.payment(
    name: KMutableProperty0<PaymentCaptureOutput?>,
    label: String = name.name,
    currency: Currency,
    formatter: MoneyFormatter,
    reference: KProperty0<P>,
    total: KProperty0<MoneyPresenter>,
    paid: KProperty0<MoneyPresenter>,
    visibility: Visibility = Visibility.Hidden,
    onChange: Changer<PaymentCaptureOutput>? = null,
    factory: ValidationFactory<PaymentCaptureOutput>? = null
) = getOrCreate(name) {
    PaymentCaptureField(name, label, visibility, currency, formatter, reference, total, paid, onChange, factory)
}