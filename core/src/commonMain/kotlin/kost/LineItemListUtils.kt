package kost

import identifier.Unique
import kollections.List
import kollections.toIList

inline fun <reified E : Unique> Collection<LineItem>.filterInstance(): List<LineItem> = buildList {
    for (item in this@filterInstance) if (item.data is E) add(item)
}.toIList()