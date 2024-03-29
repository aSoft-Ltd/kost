@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import cinematic.mutableLiveOf
import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter
import kollections.emptyList
import kollections.isNotEmpty
import koncurrent.Later
import koncurrent.later.then
import koncurrent.later.andThen
import koncurrent.later.andZip
import koncurrent.later.zip
import koncurrent.later.catch
import kotlinx.JsExport
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0
import krono.Clock
import neat.ValidationFactory
import neat.Validity
import neat.custom
import neat.required
import symphony.Changer
import symphony.Feedbacks
import symphony.Field
import symphony.SubmitConfig
import symphony.Visibilities
import symphony.Visibility
import symphony.internal.AbstractHideable
import symphony.properties.Settable
import symphony.toErrors
import symphony.toForm
import symphony.toWarnings

class PaymentCaptureField<out T>(
    private val property: KMutableProperty0<PaymentCaptureOutput?>,
    label: String,
    visibility: Visibility,
    currency: Currency,
    formatter: MoneyFormatter,
    referenceProperty: KProperty0<T>,
    totalProperty: KProperty0<MoneyPresenter>,
    paidProperty: KProperty0<MoneyPresenter>,
    clock: Clock,
    private val onChange: Changer<PaymentCaptureOutput>?,
    factory: ValidationFactory<PaymentCaptureOutput>?
) : AbstractHideable(), Field<PaymentCaptureOutput, PaymentCaptureFieldState>, Settable<PaymentCaptureOutput> {

    protected val validator = custom<PaymentCaptureOutput>(label).configure(factory)

    val form = PaymentCaptureFields(
        currency = currency,
        formatter = formatter,
        referenceProperty = referenceProperty,
        totalProperty = totalProperty,
        paidProperty = paidProperty,
        clock = clock,
    ).toForm(
        heading = "Payment Capture Form",
        details = "Capture a payment",
        visibility = Visibilities.Hidden,
        config = SubmitConfig(exitOnSuccess = false)
    ) {
        onSubmit { set(it); Later(it) }
    }

    override fun set(value: PaymentCaptureOutput?) {
        property.set(value)
        val res = validator.validate(value)
        state.value = state.value.copy(
            output = res.value,
            feedbacks = Feedbacks(res.toWarnings())
        )
        onChange?.invoke(res.value)
    }

    private val initial = PaymentCaptureFieldState(
        output = property.get(),
        required = this.validator.required,
        visibility = visibility,
        feedbacks = Feedbacks(emptyList())
    )

    override val state = mutableLiveOf(initial)

    override fun validate() = validator.validate(output)

    override fun validateToErrors(): Validity<PaymentCaptureOutput> {
        val res = validator.validate(output)
        val errors = res.toErrors()
        if (errors.isNotEmpty()) {
            state.value = state.value.copy(feedbacks = Feedbacks(errors))
        }
        return res
    }

    override fun clear() = set(null)

    override fun reset() {
        property.set(initial.output)
        val res = validator.validate(initial.output)
        state.value = state.value.copy(
            output = res.value,
            feedbacks = Feedbacks(res.toWarnings())
        )
        onChange?.invoke(res.value)
    }

    override fun finish() {
        form.exit()
        state.stopAll()
        state.history.clear()
    }

    override fun setVisibility(v: Visibility) {
        state.value = state.value.copy(visibility = v)
    }

    val reference get() = form.fields.reference
    val total get() = form.fields.total
    val paid get() = form.fields.paid
    val unpaid get() = form.fields.unpaid

    override val output get() = state.value.output
    override val required get() = state.value.required
    override val feedbacks get() = state.value.feedbacks
    override val visibility get() = state.value.visibility
}