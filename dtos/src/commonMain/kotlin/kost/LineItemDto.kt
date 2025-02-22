@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import books.FinancialAccountDto
import identifier.Unique
import kash.ZeroCents
import kollections.List
import kommerce.Offerable
import kost.params.LineItemParams
import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
data class LineItemDto(
    override val uid: String,
    val data: Offerable,
    val inventoryUid: String? = null,
    val unit: UnitDto,
    val status: TaskStatus,
    val details: String,
    val quantity: Double,
    val ref: VendorReference,
    val photos: List<String>,
    val taxes: TaxesDto,
    val account: FinancialAccountDto?,
    val discount: LineItemDiscountDto,
):Unique {
    val price by lazy {
        PriceDto(
            buying = run {
                val priceTimesQty = unit.price.buying.before.discount * quantity

                val beforeAmount = if (taxes.inclusive) {
                    taxes.subTotal(priceTimesQty)
                } else {
                    priceTimesQty
                }
                val taxes = if (taxes.inclusive) {
                    priceTimesQty - beforeAmount
                } else {
                    taxes.total(beforeAmount)
                }

                CostDto(
                    beforeDiscount = beforeAmount,
                    discount = ZeroCents,
                    taxes = taxes
                )
            },
            selling = run {
                val beforeDiscount = unit.price.selling.before.discount * quantity
                val afterDiscount = beforeDiscount - discount.total

                val beforeAmount = if (taxes.inclusive) {
                    taxes.subTotal(beforeDiscount)
                } else {
                    beforeDiscount
                }
                val taxes = if (taxes.inclusive) {
                    beforeDiscount - beforeAmount
                } else {
                    taxes.total(beforeAmount)
                }

                CostDto(
                    beforeDiscount = beforeAmount,
                    discount = discount.total,
                    taxes = taxes
                )
            }
        )
    }

    private fun includedTax() {

    }

    val profit by lazy {
        val gross = price.selling.after.tax - price.buying.after.tax
        val sell = price.selling.after.tax
        val buy = price.buying.after.tax
        ProfitDto(
            gross = gross,
            margin = run {
                if (sell == ZeroCents) return@lazy 0.0
                gross.asDouble * 100 / sell.asDouble
            },
            markup = run {
                if (buy == ZeroCents) return@lazy 0.0
                gross.asDouble * 100 / buy.asDouble
            }
        )
    }

    fun toParams() = LineItemParams(
        data = data,
        inventoryUid = inventoryUid,
        unitPrice = unit.price.selling.after.discount,
        details = details,
        quantity = quantity,
        unit = unit.measure,
        unitDiscount = discount.unit(),
        taxes = taxes.items,
        overallDiscount = discount.overall(),
        account = account
    )
}