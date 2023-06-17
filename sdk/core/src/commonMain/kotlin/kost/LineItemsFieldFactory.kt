package kost

import kollections.MutableList
import koncurrent.Later
import kost.params.LineItemParams
import lexi.Logger
import symphony.Fields
import symphony.FormConfig
import symphony.getOrCreate
import kotlin.reflect.KMutableProperty0

fun Fields<*>.lineItems(
    name: KMutableProperty0<MutableList<Intention<String, LineItemParams>>>
) = getOrCreate(name) {
    LineItemsForm(FormConfig(Logger(), exitOnSubmitted = false)) {
        onSubmit { name.set(it.intentions); Later(it) }
    }
}