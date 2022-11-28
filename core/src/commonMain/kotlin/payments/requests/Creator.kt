package payments.requests

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
class Creator(
    val uid: String,
    val name: String
)