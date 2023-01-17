package kost

import kollections.List
import kollections.iListOf
import kost.internal.ItemizedCalculableImpl

inline fun ItemizedCalculable(
    items: List<LineItem> = iListOf(),
    discount: Long = 0L,
    taxAmount: Long = 0L,
): ItemizedCalculable = ItemizedCalculableImpl(items, discount, taxAmount)