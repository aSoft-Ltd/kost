@file:JsExport
package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@Serializable
class Receiver @JvmOverloads constructor(
    val uid: String,
    val name: String,
    val address: Address = Address.Unset,
    val ref: VendorReference = VendorReference.UNSET
)