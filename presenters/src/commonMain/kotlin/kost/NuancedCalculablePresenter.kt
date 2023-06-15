@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

interface NuancedCalculablePresenter : LineItemsCalculablePresenter {
    val tax: TaxPresenter?
    val discount: OverallDiscountPresenter
}