@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport

data class PricePresenter(
    val src: PriceDto,
    val buying: CostPresenter,
    val selling: CostPresenter
)