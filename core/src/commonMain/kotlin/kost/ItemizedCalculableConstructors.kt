package kost

import kash.Monetary
import kollections.List
import kollections.iListOf
import kost.internal.ItemizedCalculableImpl

inline fun ItemizedCalculable(
    items: List<LineItem> = iListOf(),
    discount: Monetary = Monetary(0)
): ItemizedCalculable = ItemizedCalculableImpl(items, discount)