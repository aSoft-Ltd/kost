@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

interface ItemCalculablePresenter : CalculablePresenter {
    val taxes: TaxesPresenter
    val discount: LineItemDiscountPresenter
}