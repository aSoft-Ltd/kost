@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import kash.Cents
import kotlin.js.JsExport

data class TaxAmountParams(
    override val name: String,
    val amount: Cents
): TaxParams