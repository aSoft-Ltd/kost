@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

interface LineItemCalculablePresenter : CalculablePresenter {
    val taxes: TaxesPresenter
    val discount: LineItemDiscountPresenter
}