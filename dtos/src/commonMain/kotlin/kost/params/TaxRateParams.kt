@file:JsExport
package kost.params

import kotlinx.JsExport

data class TaxRateParams(
    override val name: String,
    val rate: Int
): TaxParams