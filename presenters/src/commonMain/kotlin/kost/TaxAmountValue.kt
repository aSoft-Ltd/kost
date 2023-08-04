@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

data class TaxAmountValue(
    override val src: TaxAmountDto,
    override val name: String,
    override val total: MoneyPresenter
) : TaxValue