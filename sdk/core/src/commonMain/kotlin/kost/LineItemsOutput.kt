package kost

import kollections.List
import kollections.MutableList
import kollections.iEmptyList
import kollections.iMutableListOf
import kost.params.LineItemParams

class LineItemsOutput(
    var items: List<LineItemPresenter> = iEmptyList(),
    val intentions: MutableList<Intention<String, LineItemParams>> = iMutableListOf(),
)