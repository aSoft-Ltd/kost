@file:JsExport
package kost

import kotlin.js.JsExport

data class UnitPresenter(
    val src: UnitDto,
    val price: PricePresenter,
    val measure: String
)