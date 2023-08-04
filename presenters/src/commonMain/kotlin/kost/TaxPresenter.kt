@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

sealed interface TaxPresenter {
    val src: TaxDto
    val name: String
    val total: MoneyPresenter
}