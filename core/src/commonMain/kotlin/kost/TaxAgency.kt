@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlinx.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

@Serializable
data class TaxAgency @JvmOverloads constructor(
    val name: String,
    val ref: VendorReference = VendorReference.UNSET
) {
    companion object {
        @JvmField
        val GENERIC = TaxAgency("Generic Tax Agency")
    }
}