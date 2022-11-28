@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

@Serializable
class Tax @JvmOverloads constructor(
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
        val GENERIC_ZERO = Tax("ZERO", 0, TaxAgency.GENERIC)
    }

    fun copy(
        name: String = this.name,
        rate: Int = this.rate,
        agency: TaxAgency = this.agency,
        ref: VendorReference = this.ref
    ) = Tax(name, rate, agency, ref)
}