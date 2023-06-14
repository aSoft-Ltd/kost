@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

sealed interface LineItemsDiscountPresenter {
    val total: MoneyPresenter
}