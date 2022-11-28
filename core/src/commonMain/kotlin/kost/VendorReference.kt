@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmField

@Serializable
class VendorReference(
    val uid: String,
    val name: String
) {
    companion object {
        @JvmField
        val UNSET = VendorReference("<unset>", "<unset>")
    }
}