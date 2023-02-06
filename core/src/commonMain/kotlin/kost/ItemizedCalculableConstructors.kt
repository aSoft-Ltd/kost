package kost

import kash.Monetary
import kash.Money
import kollections.List
import kollections.iListOf
import kost.internal.ItemizedCalculableImpl

inline fun ItemizedCalculable(
    items: List<LineItem> = iListOf(),
    discount: Monetary = Money(0)
): ItemizedCalculable = ItemizedCalculableImpl(items, discount)