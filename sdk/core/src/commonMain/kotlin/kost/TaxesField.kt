@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import cinematic.mutableLiveListOf
import kollections.List
import kollections.iEmptyList
import kollections.toIList
import koncurrent.Later
import symphony.SubmitConfig
import symphony.internal.AbstractBaseField
import symphony.internal.Changer
import symphony.toForm
import kotlin.js.JsExport
import kotlin.reflect.KMutableProperty0

class TaxesField(
    name: KMutableProperty0<List<Tax>>,
    label: String,
    src: Collection<Tax>,
    value: List<Tax>,
    hidden: Boolean,
    hint: String,
    onChange: Changer<List<Tax>>?,
) : AbstractBaseField<List<Tax>>(name, label, value, hidden, hint, onChange, null) {
    val source = mutableLiveListOf(*src.toTypedArray())

    val tax = TaxFields().toForm(
        heading = "Tax Form",
        details = "Add a new tax",
        config = SubmitConfig()
    ) {
        onSubmit {
            source.add(it)
            add(it)
            Later(it)
        }
    }

    fun add(tax: Tax) = set((output + tax).toIList())

    fun remove(tax: Tax) = set((output - tax).toIList())

    override fun finish() {
        tax.finish()
        super.finish()
    }

    override val output: List<Tax> get() = state.value.output ?: iEmptyList()
}