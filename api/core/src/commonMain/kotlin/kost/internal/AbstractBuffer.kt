package kost.internal

import kase.Failure
import kase.Result
import kollections.List
import kollections.toIList
import koncurrent.Later
import kost.RemoteBuffer
import kost.CreateIntention
import kost.DeleteIntention
import kost.Intention
import kost.UpdateIntention

class AbstractBuffer<I : Any, P : Any, R>(
    val intentions: MutableList<Intention<I, P>> = mutableListOf()
) : RemoteBuffer<I, P, R> {
    override fun create(params: P) {
        intentions.add(CreateIntention(params))
    }

    override fun update(uid: I, params: P) {
        intentions.add(UpdateIntention(uid, params))
    }

    override fun delete(uid: I) {
        intentions.add(DeleteIntention(uid))
    }

    override fun flush(): Later<List<Result<R>>> {
        val res = intentions.map {
            Failure<R>(NotImplementedError("ddd"))
        }
        return Later(res.toIList())
    }
}