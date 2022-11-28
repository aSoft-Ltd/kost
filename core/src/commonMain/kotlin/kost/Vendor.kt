@file:JsExport

package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
open class Vendor(
    val name: String
) {
    companion object {
        val GENERIC = Vendor("Generic Vendor")
    }
}