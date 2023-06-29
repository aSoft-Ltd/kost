package kost

import kase.catching
import neat.required

class TaxOutput(
    var name: String?,
    var rate: Int?,
) {
    fun toTax() = catching {
        Tax(::name.required, ::rate.required)
    }
}