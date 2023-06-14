@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import kash.MoneyPresenter
import kollections.List
import kommerce.Offerable
import kotlin.js.JsExport

data class LineItemPresenter(
    val uid: String,
    val data: Offerable,
    val unitPrice: MoneyPresenter,
    val status: TaskStatus,
    val details: String,
    val quantity: Double,
    val unit: String,
    val ref: VendorReference,
    val photos: List<String>,
    override val taxes: TaxesPresenter,
    override val discount: LineItemDiscountPresenter,
    override val costBeforeDiscount: MoneyPresenter,
    override val costBeforeTax: MoneyPresenter,
    override val costAfterTax: MoneyPresenter,
) : ItemCalculablePresenter