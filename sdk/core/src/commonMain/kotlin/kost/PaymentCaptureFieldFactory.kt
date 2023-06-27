package kost

import kash.MoneyPresenter
import kash.ZeroCents
import neat.ValidationFactory
import symphony.Fields
import symphony.internal.Changer
import kotlin.reflect.KMutableProperty0

fun <T : PaymentCaptureOutput?, P> Fields<*>.payment(
    name: KMutableProperty0<T>,
    label: String = name.name,
    reference: () -> P,
    total: MoneyPresenter,
    paid: MoneyPresenter = total.copy(cents = ZeroCents),
    unpaid: MoneyPresenter = total,
    value: T = name.get(),
    hidden: Boolean = false,
    hint: String = label,
    onChange: Changer<T>? = null,
    factory: ValidationFactory<T>? = null
) = getOrCreate(name) {
    PaymentCaptureField(name, label, value, hidden, hint, reference, total, paid, unpaid, onChange, factory)
}