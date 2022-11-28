package payments.requests

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@JsExport
@Serializable
class Sender @JvmOverloads constructor(
    val uid: String,
    val name: String,
    val address: Address = Address.Unset,
    val ref: VendorReference = VendorReference.UNSET,
    val logo: String? = null
)