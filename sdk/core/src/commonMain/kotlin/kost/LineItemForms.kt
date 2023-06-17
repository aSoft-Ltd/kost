package kost

import cinematic.mutableLiveListOf
import kase.Failure
import kollections.MutableList
import kollections.iMutableListOf
import koncurrent.Laters
import kost.params.LineItemParams

class LineItemForms(initial: Array<LineItemForm>) {
    val forms = mutableLiveListOf(*initial)
    val intentions: MutableList<Intention<String, LineItemParams>> = iMutableListOf()

    fun add(form: LineItemForm) = forms.add(form)

    fun beforeSubmit() = Laters(*forms.value.map { it.submit() }.toTypedArray()).then {
        val failures = it.filterIsInstance<Failure<*>>()
        if (failures.isNotEmpty()) {
            throw RuntimeException("Some line item forms still have errors")
        }
    }
}