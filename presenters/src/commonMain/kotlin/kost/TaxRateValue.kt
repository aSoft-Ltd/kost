@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.MoneyPresenter
import kotlin.js.JsExport

data class TaxRateValue(
    override val src: TaxRateDto,
    override val name: String,
    val rate: Int,
    override val total: MoneyPresenter,
) : TaxValue