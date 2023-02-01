package kost.internal

import kash.Monetary
import kollections.List
import kost.ItemizedCalculable
import kost.LineItem

@PublishedApi
internal class ItemizedCalculableImpl(
    override val items: List<LineItem>,
    override val discount: Monetary,
    override val taxAmount: Monetary,
) : ItemizedCalculable {
    override val compoundDiscount: Monetary = discount
}