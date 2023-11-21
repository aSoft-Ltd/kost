@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import kash.Cents
import kash.ZeroCents
import kollections.List
import kommerce.Offerable
import kost.params.LineItemParams
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class LineItemDto(
    val uid: String,
    val data: Offerable,
    val unit: UnitDto,
    val status: TaskStatus,
    val details: String,
    val quantity: Double,
    val ref: VendorReference,
    val photos: List<String>,
    val taxes: TaxesDto,
    val discount: LineItemDiscountDto,
) {
    val price by lazy {
        PriceDto(
            buying = run {
                val beforeDiscount = unit.price.buying.before.discount * quantity
                val afterDiscount = beforeDiscount
                CostDto(
                    beforeDiscount = beforeDiscount,
                    discount = ZeroCents,
                    taxes = taxes.total(afterDiscount)
                )
            },
            selling = run {
                val beforeDiscount = unit.price.selling.before.discount * quantity
                val afterDiscount = beforeDiscount - discount.total
                CostDto(
                    beforeDiscount = beforeDiscount,
                    discount = discount.total,
                    taxes = taxes.total(afterDiscount)
                )
            }
        )
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
        unitPrice = unit.price.selling.after.discount,
        details = details,
        quantity = quantity,
        unit = unit.measure,
        unitDiscount = discount.unit(),
        taxes = taxes.items,
        overallDiscount = discount.overall()
    )
}