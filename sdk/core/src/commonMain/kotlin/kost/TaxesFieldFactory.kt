package kost

import kollections.List
import symphony.Fields
import kotlin.reflect.KMutableProperty0

fun Fields<*>.taxes(
    name: KMutableProperty0<List<Tax>>,
    src: Collection<Tax>,
    value: List<Tax> = name.get(),
    label: String = name.name,
    hidden: Boolean = false,
    hint: String = label
) = getOrCreate(name) {
    TaxesField(name, label, src, value, hidden, hint)
}