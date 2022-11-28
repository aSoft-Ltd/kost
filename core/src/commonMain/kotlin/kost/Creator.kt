@file:JsExport
package kost

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class Creator(
    val uid: String,
    val name: String
)