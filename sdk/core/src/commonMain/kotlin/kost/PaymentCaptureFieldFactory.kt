package kost

import kash.MoneyPresenter
import koncurrent.Later
import symphony.Fields
import symphony.toInput
import kotlin.reflect.KMutableProperty0

fun <T : PaymentCaptureOutput?> Fields<*>.payment(
    name: KMutableProperty0<T>,
    total: MoneyPresenter,
    unpaid: MoneyPresenter = total,
) = getOrCreate(name) {
    PaymentCaptureFields(unpaid).toInput {
        onSubmit { output ->
            Later(output).then { it as T }.then { name.set(it) }
        }
    }
}