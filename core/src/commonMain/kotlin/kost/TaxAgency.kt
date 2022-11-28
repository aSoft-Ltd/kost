@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

@Serializable
class TaxAgency @JvmOverloads constructor(
    val name: String,
    val ref: VendorReference = VendorReference.UNSET
) {
    companion object {
        @JvmField
        val GENERIC = TaxAgency("Generic Tax Agency")
    }
}