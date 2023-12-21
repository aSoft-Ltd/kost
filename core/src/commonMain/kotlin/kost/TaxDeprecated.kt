@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.serialization.Serializable
import kotlinx.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

@Serializable
data class TaxDeprecated @JvmOverloads constructor(
    val name: String,
    /**
     * must be between 0 and 100
     */
    val rate: Int,
    val agency: TaxAgency = TaxAgency.GENERIC,
    val ref: VendorReference = VendorReference.UNSET
) {
    init {
        require(rate in 0..100) { "rate must be between 0 and 100 " }
    }

    companion object {
        @JvmField
        val GENERIC_ZERO = TaxDeprecated("ZERO", 0, TaxAgency.GENERIC)
    }
}