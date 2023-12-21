@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.params

import kash.Cents
import kotlinx.JsExport

data class TaxAmountParams(
    override val name: String,
    val amount: Cents
): TaxParams