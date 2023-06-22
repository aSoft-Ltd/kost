@file:JsExport

package kost

import bee.DocumentStatus
import kash.MoneyPresenter
import kash.ZeroCents
import kollections.JsExport
import symphony.Fields
import symphony.Option
import symphony.boolean
import symphony.date
import symphony.money
import symphony.selectSingle
import symphony.text
import kost.PaymentCaptureOutput as Params

class PaymentCaptureFields(unpaid: MoneyPresenter) : Fields<Params>(Params(unpaid.amount.asDouble)) {

    val amount = money(output::amount)

    val date = date(output::date)

    val details = text(output::details)

    val to = text(output::to)

    val fullyPaid = boolean(
        name = output::fullyPaid,
        label = "Fully paid"
    )

    val status = selectSingle(
        name = output::status,
        value = DocumentStatus.Draft,
        items = DocumentStatus.values().toList(),
        mapper = { Option(it.label, it.name, false) }
    )

    val method = selectSingle(
        name = output::method,
        value = CashDto(ZeroCents, ZeroCents),
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

    val transactionId = text(output::transactionId)
}