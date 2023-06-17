package kost.transfomers

import kash.Zero
import kollections.List
import kollections.iEmptyList
import kommerce.Offerable
import koncurrent.Later
import kost.LineItemForm
import kost.LineItemOutput
import kost.LineItemPresenter
import kost.Tax
import kost.overall
import kost.unit
import lexi.Logger
import symphony.FormConfig

fun LineItemPresenter?.toOutput(offerable: Offerable) = LineItemOutput(
    offerable = offerable,
    unitPrice = this?.unitPrice?.money ?: Zero,
    details = this?.details ?: offerable.name,
    quantity = this?.quantity ?: 1.0,
    unit = this?.unit ?: "each",
    unitDiscount = this?.discount?.unit()?.money ?: Zero,
    overallDiscount = this?.discount?.overall()?.money ?: Zero,
    taxes = this?.taxes?.items?.map { Tax(it.name, it.rate) } ?: iEmptyList()
)

fun LineItemPresenter?.toForm(taxes: List<Tax>, offerable: Offerable) = LineItemForm(
    heading = "Line Item Form",
    details = "Edit line item",
    offerable = this?.data ?: offerable,
    item = this,
    taxes = taxes,
    config = FormConfig(Logger(), exitOnSubmitted = false)
) { onSubmit { Later(it) } }