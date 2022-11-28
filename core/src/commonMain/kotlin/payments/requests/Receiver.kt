package payments.requests

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@JsExport
@Serializable
class Receiver @JvmOverloads constructor(
    val uid: String,
    val name: String,
    val address: Address = Address.Unset,
    val ref: VendorReference = VendorReference.UNSET
)