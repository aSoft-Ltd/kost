@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlin.js.JsExport

data class PricePresenter(
    val src: PriceDto,
    val buying: CostPresenter,
    val selling: CostPresenter
)