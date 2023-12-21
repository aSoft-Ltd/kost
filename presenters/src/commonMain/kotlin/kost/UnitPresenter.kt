@file:JsExport
package kost

import kotlinx.JsExport

data class UnitPresenter(
    val src: UnitDto,
    val price: PricePresenter,
    val measure: String
)