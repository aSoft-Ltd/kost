@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import cinematic.mutableLiveOf
import kash.MoneyPresenter
import kollections.iEmptyList
import koncurrent.Later
import kost.PaymentCaptureField.State
import lexi.Logger
import neat.ValidationFactory
import neat.Validity
import neat.custom
import neat.required
import symphony.FieldState
import symphony.Feedbacks
import symphony.Field
import symphony.SubmitConfig
import symphony.Visibility
import symphony.internal.AbstractHideable
import symphony.Changer
import symphony.properties.Settable
import symphony.toErrors
import symphony.toForm
import symphony.toWarnings
import kotlin.js.JsExport
import kotlin.reflect.KMutableProperty0

class PaymentCaptureField<out T>(
    private val property: KMutableProperty0<PaymentCaptureOutput?>,
    label: String,
    visibility: Visibility,
    val reference: () -> T,
    val total: MoneyPresenter,
    val paid: MoneyPresenter,
    val unpaid: MoneyPresenter,
    private val onChange: Changer<PaymentCaptureOutput>?,
    factory: ValidationFactory<PaymentCaptureOutput>?
) : AbstractHideable(), Field<PaymentCaptureOutput, State>, Settable<PaymentCaptureOutput> {

    protected val validator = custom<PaymentCaptureOutput>(label).configure(factory)

    val form = PaymentCaptureFields(
        reference = reference,
        paid, unpaid, total
    ).toForm(
        heading = "Payment Capture Form",
        details = "Capture a payment",
        visibility = Visibility.Hidden,
        config = SubmitConfig(Logger(), exitOnSuccess = false)
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

    data class State(
        override val output: PaymentCaptureOutput?,
        override val required: Boolean,
        override val visibility: Visibility,
        override val feedbacks: Feedbacks
    ) : FieldState<PaymentCaptureOutput>

    private val initial = State(
        output = property.get(),
        required = this.validator.required,
        visibility = visibility,
        feedbacks = Feedbacks(iEmptyList())
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

    override val output get() = state.value.output
    override val required get() = state.value.required
    override val feedbacks get() = state.value.feedbacks
    override val visibility get() = state.value.visibility
}