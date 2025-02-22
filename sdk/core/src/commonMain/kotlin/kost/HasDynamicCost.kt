@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.JsExport

interface HasDynamicCost {
    fun cost() : CostPresenter
}