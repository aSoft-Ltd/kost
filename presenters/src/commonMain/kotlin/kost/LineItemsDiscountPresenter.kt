@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlinx.serialization.Serializable
import kotlinx.JsExport

sealed interface LineItemsDiscountPresenter {
    val total: MoneyPresenter
}