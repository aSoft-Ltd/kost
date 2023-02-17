package kost.internal

import kash.Money
import kollections.List
import kost.ItemizedCalculable
import kost.LineItem
import kost.discountOf

@PublishedApi
internal class ItemizedCalculableImpl(
    override val items: List<LineItem>,
    compoundDiscount: Money
) : ItemizedCalculable {
    override val discount = discountOf(items, compoundDiscount)
}