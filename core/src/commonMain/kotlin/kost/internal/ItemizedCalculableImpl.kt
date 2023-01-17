package kost.internal

import kollections.List
import kost.ItemizedCalculable
import kost.LineItem

@PublishedApi
internal class ItemizedCalculableImpl(
    override val items: List<LineItem>,
    override val discount: Long,
    override val taxAmount: Long,
) : ItemizedCalculable {
    override val compoundDiscount: Long = discount
}