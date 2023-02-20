@file:Suppress("NOTHING_TO_INLINE")

package kost

import kotlinx.serialization.Serializable

@Serializable
data class Intention<out I : Any, out P : Any> @PublishedApi internal constructor(
    val uid: I?,
    val params: P?,
    val type: Type,
) {
    @Serializable
    enum class Type {
        Create, Update, Delete
    }
}

inline fun <P : Any> CreateIntention(params: P) = Intention(null, params, Intention.Type.Create)

inline fun <I : Any, P : Any> UpdateIntention(uid: I, params: P) = Intention(uid, params, Intention.Type.Update)

inline fun <I : Any, P : Any> DeleteIntention(uid: I, params: P) = Intention(uid, params, Intention.Type.Delete)