package kost

import kollections.toIList
import kommerce.Offerable

inline fun <reified E : Offerable> Collection<LineItem>.filterInstance() = filter { it.data is E }.toIList()