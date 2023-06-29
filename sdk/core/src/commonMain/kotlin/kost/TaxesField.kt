@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import cinematic.mutableLiveListOf
import kollections.List
import kollections.MutableList
import koncurrent.Later
import neat.ValidationFactory
import symphony.SubmitConfig
import symphony.Visibility
import symphony.Changer
import symphony.internal.ListFieldImpl
import symphony.toForm
import kotlin.js.JsExport
import kotlin.reflect.KProperty0

class TaxesField(
    name: KProperty0<MutableList<Tax>>,
    label: String,
    src: Collection<Tax>,
    visibility: Visibility,
    onChange: Changer<List<Tax>>?,
    factory: ValidationFactory<List<Tax>>?
) : ListFieldImpl<Tax>(name, label, visibility, onChange, factory) {
    val source = mutableLiveListOf(*src.toTypedArray())

    val tax = TaxFields().toForm(
        heading = "Tax Form",
        details = "Add a new tax",
        config = SubmitConfig(),
        visibility = Visibility.Hidden
    ) {
        onSubmit {
            val tax = it.toTax().getOrThrow()
            source.add(tax)
            add(tax)
            Later(tax)
        }
    }
}