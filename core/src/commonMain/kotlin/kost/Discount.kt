@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kash.Monetary
import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
sealed interface Discount {
    val costBefore: Monetary
    val total: Monetary
    val costAfter get() = costBefore - total

    val asZero get() = this as? ZeroDiscount
    val asUnit get() = this as? UnitDiscount
    val asGlobal get() = this as? GlobalDiscount
    val asCompound get() = this as? CompoundDiscount
}

@Serializable
data class ZeroDiscount(override val costBefore: Monetary) : Discount {
    override val total = Money(0)
    override val costAfter = costBefore
}

@Serializable
data class UnitDiscount internal constructor(
    override val costBefore: Monetary,
    val rate: Monetary,
    val quantity: Double
) : Discount {
    override val total = rate * quantity
}

@Serializable
data class GlobalDiscount internal constructor(
    override val costBefore: Monetary,
    val amount: Monetary
) : Discount {
    override val total = amount
}

@Serializable
data class CompoundDiscount internal constructor(
    override val costBefore: Monetary,
    val rate: Monetary,
    val quantity: Double,
    val globalAmount: Monetary
) : Discount {
    val ratesTotal get() = rate * quantity
    val costAfterRatesTotal get() = costBefore - ratesTotal
    override val total = (rate * quantity) + globalAmount
}

internal fun discountOf(costBefore: Monetary, rate: Monetary, quantity: Double, global: Monetary): Discount = when {
    rate.centsAsLong == 0uL && global.centsAsLong == 0uL -> ZeroDiscount(costBefore)
    rate.centsAsLong == 0uL && global.centsAsLong != 0uL -> GlobalDiscount(costBefore, global)
    rate.centsAsLong != 0uL && global.centsAsLong == 0uL -> UnitDiscount(costBefore, rate, quantity)
    else -> CompoundDiscount(costBefore, rate, quantity, global)
}

fun discountOf(costBefore: Monetary, amount: Monetary): Discount = when (amount.centsAsLong) {
    0uL -> ZeroDiscount(costBefore)
    else -> GlobalDiscount(costBefore, amount)
}