@file:JsExport
package kost.params

import kotlin.js.JsExport

data class TaxRateParams(
    override val name: String,
    val rate: Int
): TaxParams