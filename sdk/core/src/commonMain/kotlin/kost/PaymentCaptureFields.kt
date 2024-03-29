@file:JsExport

package kost

import kash.Currency
import kash.MoneyFormatter
import kash.MoneyPresenter
import kash.ZeroCents
import kash.transformers.toPresenter
import kotlinx.JsExport
import krono.Clock
import krono.Instant
import symphony.Fields
import symphony.Option
import krono.date
import symphony.money
import symphony.selectSingle
import kollections.listOf
import symphony.text
import kotlin.reflect.KProperty0
import kost.PaymentCaptureOutput as Output

class PaymentCaptureFields<out T>(
    private val currency: Currency,
    private val formatter: MoneyFormatter,
    private val referenceProperty: KProperty0<T>?,
    private val totalProperty: KProperty0<MoneyPresenter>?,
    private val paidProperty: KProperty0<MoneyPresenter>?,
    private val clock: Clock,
) : Fields<Output>(
    Output(
        amountRequired = totalProperty?.get()?.amount?.asDouble ?: 0.0,
        amountPaid = paidProperty?.get()?.amount?.asDouble ?: 0.0,
        date = Instant(clock.currentMillisAsLong()).atSystemZone().date,
        currency = currency,
        formatter = formatter
    )
) {

    val amount = money(output::amount)

    val date = date(output::date)

    val details = text(output::details)

    val to = text(output::to)

    val method = selectSingle(
        name = output::method,
        items = listOf(
            CashDto(ZeroCents, ZeroCents),
            ChequeDto(""),
            DebitCardDto("", null),
            CreditCardDto("", null),
            BankDepositDto(""),
            MobilePaymentDto(""),
            ElectronicTransferDto("")
        ),
        mapper = { Option(it.typ.label, it.typ.name, false) }
    )

    fun setRemainingAmountToBePaid() {
        amount.set(unpaid.amount.asDouble.toString())
    }

    fun smartPopulate() {
        if (amount.output != null) return
        setRemainingAmountToBePaid()
    }

    val transactionId = text(output::transactionId)

    val reference get() = referenceProperty?.get()
    val total get() = totalProperty?.get() ?: Zero
    val paid get() = paidProperty?.get() ?: Zero
    val unpaid get() = (total.cents - paid.cents).toPresenter(currency, formatter)

    private val Zero = ZeroCents.toPresenter(currency, formatter)
}