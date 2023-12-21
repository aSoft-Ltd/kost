@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport
import symphony.Feedbacks
import symphony.FieldState
import symphony.Visibility

data class PaymentCaptureFieldState(
    override val output: PaymentCaptureOutput?,
    override val required: Boolean,
    override val visibility: Visibility,
    override val feedbacks: Feedbacks
) : FieldState<PaymentCaptureOutput>