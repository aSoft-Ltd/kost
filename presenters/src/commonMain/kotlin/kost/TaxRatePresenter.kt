@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

data class TaxRatePresenter(
    override val src: TaxRateDto,
    override val uid: String,
    override val name: String,
    val rate: Int,
) : TaxPresenter