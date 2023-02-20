@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost.discount

import kash.Money
import kost.CompoundLineItemDiscount
import kost.CompoundLineItemsDiscount
import kost.GlobalDiscount
import kost.GranularLineItemsDiscount
import kost.NoDiscount
import kost.UnitDiscount
import kotlin.js.JsExport

interface CanBeZero {
    val asZero: NoDiscount?
}

interface CanBeGlobal {
    val asGlobal: GlobalDiscount?
}

interface CanBeUnit {
    val asUnit: UnitDiscount?
}

interface CanBeGranular {
    val asGranular: GranularLineItemsDiscount?
}

interface CanBeCompoundForLineItem {
    val asCompound: CompoundLineItemDiscount?
}

interface CanBeCompoundForLineItems {
    val asCompound: CompoundLineItemsDiscount?
}

interface HasGlobal {
    val global: Money
}

interface HasLineItems {
    val items: Money
}