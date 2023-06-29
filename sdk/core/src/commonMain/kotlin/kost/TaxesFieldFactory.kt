package kost

import kollections.List
import kollections.MutableList
import neat.ValidationFactory
import symphony.Fields
import symphony.Visibility
import symphony.Changer
import kotlin.reflect.KProperty0

fun Fields<*>.taxes(
    name: KProperty0<MutableList<Tax>>,
    src: Collection<Tax>,
    label: String = name.name,
    visibility: Visibility = Visibility.Visible,
    onChange: Changer<List<Tax>>? = null,
    factory: ValidationFactory<List<Tax>>? = null
) = getOrCreate(name) {
    TaxesField(name, label, src, visibility, onChange, factory)
}