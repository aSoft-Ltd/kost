package kost

import kash.Money
import kash.Zero
import kollections.List
import kollections.iListOf
import kost.internal.ItemizedCalculableImpl

inline fun ItemizedCalculable(
    items: List<LineItem> = iListOf(),
    discount: Money = Zero
): ItemizedCalculable = ItemizedCalculableImpl(items, discount)