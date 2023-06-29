package kost

import kash.MoneyPresenter
import kash.ZeroCents
import neat.ValidationFactory
import symphony.Fields
import symphony.Visibility
import symphony.Changer
import kotlin.reflect.KMutableProperty0

fun <P> Fields<*>.payment(
    name: KMutableProperty0<PaymentCaptureOutput?>,
    label: String = name.name,
    reference: () -> P,
    total: MoneyPresenter,
    paid: MoneyPresenter = total.copy(cents = ZeroCents),
    unpaid: MoneyPresenter = total,
    visibility: Visibility = Visibility.Hidden,
    onChange: Changer<PaymentCaptureOutput>? = null,
    factory: ValidationFactory<PaymentCaptureOutput>? = null
) = getOrCreate(name) {
    PaymentCaptureField(name, label, visibility, reference, total, paid, unpaid, onChange, factory)
}