@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kollections.List
import kotlinx.JsExport

data class TaxesPresenter(
    val src: TaxesDto,
    val items: List<TaxValue>,
    val total: MoneyPresenter
) : List<TaxValue> by items