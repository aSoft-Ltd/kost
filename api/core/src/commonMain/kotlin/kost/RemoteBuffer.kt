@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import kase.Result
import kollections.List
import koncurrent.Later
import kotlin.js.JsExport

interface RemoteBuffer<in I, in P, out R> {
    fun create(params: P)
    fun update(uid: I, params: P)
    fun delete(uid: I)
    fun flush(): Later<List<Result<R>>>
}