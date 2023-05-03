@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import symphony.Fields
import symphony.date
import symphony.list
import symphony.money
import symphony.text
import kotlin.js.JsExport
import kost.PaymentRequestFormParams as Params

abstract class PaymentRequestFields(val subjectUid: String?, val body: ItemizedCalculable?) : Fields() {

    val issueDate = date(
        name = Params::issueDate
    )

    val customerId = text(
        name = Params::customerId,
        value = subjectUid
    )

//    val channel = selectSingle(
//        name = Params::channel,
//        items = cellar.OrderChannel.values().toList(),
//        mapper = { presenters.Option(it.name, it.name) },
//        value = order?.channel,
//        isRequired = false
//    )
//
//    val notes = text(
//        name = Params::notes,
//        value = order?.notes
//    )

    val products = list(
        name = Params::items,
        value = body?.items?.filter { it.data.asProduct != null }
    )

    //    val services = list(
//        name = Params::services,
//        value = order?.services
//    )
//
//    val customItems = list(
//        name = Params::customItems,
//        value = order?.customItems
//    )
//
    val discount = money(
        name = Params::discount,
        value = body?.discount?.total
    )
}