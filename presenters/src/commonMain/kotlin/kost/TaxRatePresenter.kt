@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport

data class TaxRatePresenter(
    override val src: TaxRateDto,
    override val uid: String,
    override val name: String,
    val rate: Int,
) : TaxPresenter