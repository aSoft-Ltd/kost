@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlinx.JsExport
import kotlin.jvm.JvmField

@Serializable
data class VendorReference(
    val uid: String,
    val name: String
) {
    companion object {
        @JvmField
        val UNSET = VendorReference("<unset>", "<unset>")
    }
}