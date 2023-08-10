@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package kost

import kost.Intention.Type.Create
import kost.Intention.Type.Delete
import kost.Intention.Type.Update
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

    fun <R : Any> map(transform: (P) -> R): Intention<I, R> = Intention(uid, params = if (params != null) transform(params) else null, type)

    fun prettyString() = type.name + " uid = $uid, params = $params"
}

inline fun <I : Any, P : Any> MutableList<Intention<I, P>>.create(params: P) {
    add(CreateIntention(params))
}

inline fun <I : Any, P : Any> MutableList<Intention<I, P>>.create(
    params: P,
    unique: (P?) -> Any?
) = append(CreateIntention(params), unique)


inline fun <I : Any, P : Any> MutableList<Intention<I, P>>.update(
    uid: I,
    params: P,
    unique: (P?) -> Any?
) = append(UpdateIntention(uid, params), unique)

inline fun <I : Any, P : Any> MutableList<Intention<I, P>>.delete(
    uid: I,
    params: P,
    unique: (P?) -> Any?
) = append(DeleteIntention(uid, params), unique)

inline fun <I : Any, P : Any> MutableList<Intention<I, P>>.append(
    i: Intention<I, P>,
    unique: (p1: P?) -> Any?
) {
    val existing = find { unique(it.params) == unique(i.params) }
    when {
        existing?.type == Create && i.type == Create -> {
            remove(existing)
            add(i)
        }

        existing?.type == Create && i.type == Update -> {
            remove(existing)
            add(existing.copy(params = i.params))
        }

        existing?.type == Create && i.type == Delete -> {
            remove(existing)
        }

        existing?.type == Update && i.type == Create -> {
            remove(existing)
            add(existing.copy(uid = existing.uid, params = i.params))
        }

        existing?.type == Update && i.type == Update -> {
            remove(existing)
            add(existing.copy(params = i.params))
        }

        existing?.type == Update && i.type == Delete -> {
            remove(existing)
            add(i)
        }

        existing?.type == Delete && i.type == Create -> {
            remove(existing)
            add(i)
        }

        existing?.type == Delete && i.type == Update -> {
            remove(existing)
            add(i)
        }

        existing?.type == Delete && i.type == Delete -> {}
        else -> add(i)
    }
}

inline fun <P : Any> CreateIntention(params: P) = Intention(null, params, Create)

inline fun <I : Any, P : Any> UpdateIntention(uid: I, params: P) = Intention(uid, params, Update)

inline fun <I : Any> DeleteIntention(uid: I) = Intention(uid, null, Delete)

inline fun <I : Any, P : Any> DeleteIntention(uid: I, params: P?) = Intention(uid, params, Delete)