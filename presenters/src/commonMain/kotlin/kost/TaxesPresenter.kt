@file:JsExport

package kost

import kollections.List
import kotlin.js.JsExport

data class TaxesPresenter(
    val items: List<TaxPresenter>
) : List<TaxPresenter> by items