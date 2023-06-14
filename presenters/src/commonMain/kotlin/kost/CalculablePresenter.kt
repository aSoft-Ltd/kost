@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

interface CalculablePresenter {
    val costBeforeDiscount: MoneyPresenter
    val costBeforeTax: MoneyPresenter
    val costAfterTax: MoneyPresenter
}