@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

sealed interface TaxPresenter {
    val src: TaxDto
    val uid: String
    val name: String

    val asAmount get() = this as? TaxAmountPresenter
    val asRate get() = this as? TaxRatePresenter
}