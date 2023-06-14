@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

interface NuancedCalculablePresenter : CalculablePresenter {
    val tax: TaxPresenter
    val discount: OverallDiscountPresenter
}