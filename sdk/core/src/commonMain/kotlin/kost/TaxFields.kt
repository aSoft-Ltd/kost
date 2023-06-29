@file:JsExport
@file:Suppress("OPT_IN_USAGE", "NON_EXPORTABLE_TYPE")

package kost

import symphony.Fields
import symphony.integer
import symphony.name
import kotlin.js.JsExport

class TaxFields : Fields<TaxOutput>(TaxOutput("", 0)) {
    val name = name(output::name)
    val rate = integer(output::rate)
}