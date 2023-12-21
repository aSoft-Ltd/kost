@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport

interface LineItemCalculablePresenter : CalculablePresenter {
    val taxes: TaxesPresenter
    val discount: LineItemDiscountPresenter
}