@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kotlinx.serialization.Serializable
import kotlinx.JsExport

@Serializable
data class LineItemRef(
    val uid: String,
    val details: String,
    val unit: String = "each",
)