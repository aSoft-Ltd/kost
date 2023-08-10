@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

data class TaxAmountPresenter(
    override val src: TaxAmountDto,
    override val uid: String,
    override val name: String,
    val amount: MoneyPresenter
) : TaxPresenter