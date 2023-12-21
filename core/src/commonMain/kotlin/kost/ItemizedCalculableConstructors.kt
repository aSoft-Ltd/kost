package kost

import kash.Money
import kash.Zero
import kollections.List
import kollections.listOf
import kost.internal.ItemizedCalculableImpl

inline fun ItemizedCalculable(
    items: List<LineItem> = listOf(),
    discount: Money = Zero
): ItemizedCalculable = ItemizedCalculableImpl(items, discount)