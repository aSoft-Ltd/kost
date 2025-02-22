package kost

import cinematic.MutableLive
import cinematic.mutableLiveOf
import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter
import kollections.List
import koncurrent.Later
import krono.Clock
import neat.ValidationFactory
import symphony.Changer
import symphony.Fields
import symphony.Visibility
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0
import symphony.Visibilities

fun <P> Fields<*>.payment(
    name: KMutableProperty0<PaymentCaptureOutput?>,
    label: String = name.name,
    currency: Currency,
    formatter: MoneyFormatter,
    reference: KProperty0<P>,
    total: KProperty0<MoneyPresenter>,
    paid: KProperty0<MoneyPresenter>,
    clock: Clock,
    visibility: Visibility = Visibilities.Hidden,
    onChange: Changer<PaymentCaptureOutput>? = null,
    factory: ValidationFactory<PaymentCaptureOutput>? = null,
    gateways:MutableLive<List<PaymentCaptureGateway<*>>>
) = getOrCreate(name) {
    PaymentCaptureField(name, label, visibility, currency, formatter, reference, total, paid, clock, onChange, factory, gateways)
}