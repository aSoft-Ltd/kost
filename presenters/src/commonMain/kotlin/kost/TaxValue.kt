@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlinx.JsExport

sealed interface TaxValue {
    val src: TaxDto
    val name: String
    val total: MoneyPresenter
}