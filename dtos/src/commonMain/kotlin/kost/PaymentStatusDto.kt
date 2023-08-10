@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Cents
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed interface PaymentStatusDto {
    val amount: Cents

    val asUnPaid get() = this as? UnpaidDto
    val asPartiallyPaid get() = this as? PartiallyPaidDto
    val asFullPaid get() = this as? FullyPaidDto
    val asOverPaid get() = this as? OverPaidDto
}

@Serializable
data class UnpaidDto(
    override val amount: Cents
) : PaymentStatusDto

@Serializable
data class PartiallyPaidDto(
    val payments: List<PaymentDto>,
    override val amount: Cents,
    val total: Cents
) : PaymentStatusDto {
    val remaining = total - amount
}

@Serializable
data class FullyPaidDto(
    val payments: List<PaymentDto>,
    override val amount: Cents
) : PaymentStatusDto

@Serializable
data class OverPaidDto(
    val payments: List<PaymentDto>,
    override val amount: Cents,
    val total: Cents,
) : PaymentStatusDto {
    val surplus = amount - total
}