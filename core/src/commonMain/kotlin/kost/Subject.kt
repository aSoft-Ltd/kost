@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
data class Subject(
    val uid: String,
    val name: String,
    val address: Address = Address.Unset,
    val ref: VendorReference = VendorReference.UNSET,
    val logo: String? = null
)