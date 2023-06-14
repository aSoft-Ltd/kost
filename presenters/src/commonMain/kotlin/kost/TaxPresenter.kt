@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

data class TaxPresenter(
    val name: String,
    val rate: Int,
    val amount: MoneyPresenter
)