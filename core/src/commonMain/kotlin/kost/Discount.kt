@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kash.Money
import kash.totalOf
import kost.discount.CanBeCompoundForLineItem
import kost.discount.CanBeCompoundForLineItems
import kost.discount.CanBeGlobal
import kost.discount.CanBeGranular
import kost.discount.CanBeUnit
import kost.discount.CanBeZero
import kost.discount.HasGlobal
import kost.discount.HasLineItems
import kotlin.js.JsExport

sealed interface Discount {
    val costBefore: Monetary
    val total: Monetary
    val costAfter get() = costBefore - total
}

/**
 * Can be
 * 1. NoDiscount : For no discounts
 * 2. UnitDiscount: For unit discounts
 * 3. GlobalDiscount: For global discount only
 * 4. CompoundLineItemDiscount: For a mixture of global and compound discount
 */
sealed interface LineItemDiscount : Discount, CanBeZero, CanBeUnit, CanBeGlobal, CanBeCompoundForLineItem

/**
 * Can be
 * 1. NoDiscount : For no discounts
 * 3. GlobalDiscount: For global discount only
 * 4. CompoundLineItemsDiscount: For a mixture of global and compound discount
 */
sealed interface LineItemsDiscount : Discount, CanBeZero, CanBeGranular, CanBeGlobal, CanBeCompoundForLineItems

data class NoDiscount(override val costBefore: Monetary) : LineItemDiscount, LineItemsDiscount {
    override val total = Money(0)
    override val costAfter = costBefore

    override val asZero: NoDiscount = this
    override val asUnit: Nothing? = null
    override val asGranular: Nothing? = null
    override val asCompound: Nothing? = null
    override val asGlobal: Nothing? = null
}

data class UnitDiscount internal constructor(
    override val costBefore: Monetary,
    val rate: Monetary,
    val quantity: Double
) : LineItemDiscount {
    override val total = rate * quantity

    override val asZero: Nothing? = null
    override val asUnit: UnitDiscount = this
    override val asCompound: Nothing? = null
    override val asGlobal: Nothing? = null
}

data class GlobalDiscount internal constructor(
    override val costBefore: Monetary,
    override val global: Monetary
) : LineItemDiscount, LineItemsDiscount, HasGlobal {
    override val asZero: Nothing? = null
    override val asUnit: Nothing? = null
    override val asCompound: Nothing? = null
    override val asGlobal: GlobalDiscount = this
    override val asGranular: Nothing? = null
    override val total = global
}

data class CompoundLineItemDiscount internal constructor(
    override val costBefore: Monetary,
    val rate: Monetary,
    val quantity: Double,
    override val global: Monetary
) : LineItemDiscount, HasGlobal {
    val ratesTotal = rate * quantity
    val costBeforeGlobal get() = costBefore - ratesTotal
    override val total = (rate * quantity) + global

    override val asZero: Nothing? = null
    override val asUnit: Nothing? = null
    override val asCompound: CompoundLineItemDiscount = this
    override val asGlobal: Nothing? = null
}

data class CompoundLineItemsDiscount internal constructor(
    override val costBefore: Monetary,
    override val items: Monetary,
    override val global: Monetary
) : LineItemsDiscount, HasGlobal, HasLineItems {
    val costBeforeGlobal = costBefore - items
    override val total: Monetary = items + global

    override val asZero: Nothing? = null
    override val asCompound: CompoundLineItemsDiscount = this
    override val asGlobal: Nothing? = null
    override val asGranular: Nothing? = null
}

data class GranularLineItemsDiscount internal constructor(
    override val costBefore: Monetary,
    override val items: Monetary,
) : LineItemsDiscount, HasLineItems {
    override val total: Monetary = items

    override val asZero: Nothing? = null
    override val asCompound: Nothing? = null
    override val asGlobal: Nothing? = null
    override val asGranular: GranularLineItemsDiscount = this
}

internal fun discountOf(costBefore: Monetary, rate: Monetary, quantity: Double, global: Monetary): LineItemDiscount = when {
    rate.centsAsLong == 0uL && global.centsAsLong == 0uL -> NoDiscount(costBefore)
    rate.centsAsLong == 0uL && global.centsAsLong != 0uL -> GlobalDiscount(costBefore, global)
    rate.centsAsLong != 0uL && global.centsAsLong == 0uL -> UnitDiscount(costBefore, rate, quantity)
    else -> CompoundLineItemDiscount(costBefore, rate, quantity, global)
}

fun discountOf(items: Collection<LineItem>, global: Monetary): LineItemsDiscount {
    val costBefore = items.totalOf { it.discount.costBefore }
    val itemsDiscount = items.totalOf { it.discount.total }
    return when {
        itemsDiscount.centsAsLong == 0uL && global.centsAsLong == 0uL -> NoDiscount(costBefore)
        itemsDiscount.centsAsLong == 0uL && global.centsAsLong != 0uL -> GlobalDiscount(costBefore, global)
        itemsDiscount.centsAsLong != 0uL && global.centsAsLong == 0uL -> GranularLineItemsDiscount(costBefore, itemsDiscount)
        else -> CompoundLineItemsDiscount(costBefore, itemsDiscount, global)
    }
}