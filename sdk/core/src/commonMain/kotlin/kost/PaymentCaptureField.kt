@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kollections.iEmptyList
import koncurrent.Later
import kost.PaymentCaptureField.State
import lexi.Logger
import neat.ValidationFactory
import neat.required
import symphony.Feedbacks
import symphony.FieldState
import symphony.Label
import symphony.SubmitConfig
import symphony.internal.AbstractField
import symphony.internal.Changer
import symphony.properties.Settable
import symphony.toForm
import symphony.toWarnings
import kotlin.js.JsExport
import kotlin.reflect.KMutableProperty0

class PaymentCaptureField<O : PaymentCaptureOutput?, out T>(
    val name: KMutableProperty0<O>,
    label: String,
    value: O,
    hidden: Boolean,
    hint: String,
    val reference: () -> T,
    val total: MoneyPresenter,
    val paid: MoneyPresenter,
    val unpaid: MoneyPresenter,
    private val onChange: Changer<O>?,
    factory: ValidationFactory<O>?
) : AbstractField<O, State<O>>(label, factory), Settable<O> {

    val form = PaymentCaptureFields(
        reference = reference,
        paid, unpaid, total
    ).toForm(
        heading = "Payment Capture Form",
        details = "Capture a payment",
        config = SubmitConfig(Logger(), exitOnSuccess = false)
    ) {
        onSubmit { set(it as O); Later(it) }
    }

    init {
        form.hide()
    }

    override fun set(value: O) {
        val res = validator.validate(value)
        name.set(res.value)
        state.value = state.value.copy(
            output = res.value,
            feedbacks = Feedbacks(res.toWarnings())
        )
        onChange?.invoke(res.value)
    }

    override fun cleared() = initial.copy(output = null)

    override fun State<O>.with(
        visibility: Boolean,
        feedbacks: Feedbacks
    ) = copy(hidden = visibility, feedbacks = feedbacks)

    data class State<out O>(
        override val output: O?,
        override val label: Label,
        override val required: Boolean,
        override val hint: String,
        override val hidden: Boolean,
        override val feedbacks: Feedbacks
    ) : FieldState<O>

    override val initial = State(
        output = value,
        label = Label(label, this.validator.required),
        required = this.validator.required,
        hint = hint,
        hidden = hidden,
        feedbacks = Feedbacks(iEmptyList())
    )
}