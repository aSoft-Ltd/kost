@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import cinematic.mutableLiveListOf
import cinematic.mutableLiveOf
import kollections.List
import kommerce.Offerable
import koncurrent.Later
import kost.transfomers.toOutput
import symphony.Form
import symphony.FormConfig
import symphony.FormInitialzer
import symphony.toFormConfig
import kotlin.js.JsExport

class LineItemForm(
    heading: String,
    details: String,
    offerable: Offerable,
    val item: LineItemPresenter?,
    taxes: List<Tax>,
    config: FormConfig,
    initializer: FormInitialzer<LineItemOutput, LineItemOutput>
) : Form<LineItemFields, LineItemOutput, LineItemOutput>(
    heading, details, LineItemFields(item.toOutput(offerable)), config, initializer
) {
    val taxes = mutableLiveListOf<Tax>(*taxes.toTypedArray())

    val tax = mutableLiveOf<TaxForm?>(null)

    fun showAddTaxForm() {
        tax.value = TaxForm(
            heading = "Tax Form",
            details = "Add a new Tax",
            config = config.toFormConfig()
        ) {
            onCancel { tax.value = null }
            onSubmit {
                taxes.add(it)
                fields.taxes.add(it);
                Later(it)
            }
        }
    }

    fun hideTaxForm() {
        tax.value = null
    }

    fun addTax(tax: Tax) = fields.taxes.add(tax)
}