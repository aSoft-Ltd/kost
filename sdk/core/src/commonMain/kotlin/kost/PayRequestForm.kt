@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bee.TaskStatus
import identifier.UNSET
import kash.Monetary
import kollections.List
import kost.params.LineItemParams
import presenters.Form
import presenters.FormActionsBuildingBlock
import presenters.FormConfig
import stocker.products.Product
import kotlin.js.JsExport

abstract class PayRequestForm<out F : PaymentRequestFields, out P, out R>(
    override val heading: String,
    override val details: String,
    open val record: ItemizedCalculable?,
    override val fields: F,
    override val config: FormConfig<@UnsafeVariance P>,
    initializer: FormActionsBuildingBlock<P, R>,
) : Form<F, P, R>(heading, details, fields, config, initializer) {

    val summary by lazy { LineItems(config).apply { update(record) } }

    private fun updateSummary() {
        val items = fields.products.data.value.output
        val discount = fields.discount.data.value.output ?: Monetary(0)
        summary.update(ItemizedCalculable(items, discount))
    }

    private fun addSingleProduct(p: Product) {
        val products = fields.products
        val item = products.data.value.output.find { it.data.uid == p.uid }
        if (item == null) {
            val quantity = 1.0
            products.add(p.toLineItem(quantity))
            summary.intentions.create(p.toLineItemParams(quantity), unique)
        } else {
            val i = item.copy(quantity = item.quantity + 1.0)
            products.update(item) { i }
            summary.intentions.update(i.uid, i.toParams(), unique)
        }
    }

    fun addProduct(product: Product) {
        addSingleProduct(product)
        updateSummary()
    }

    fun addProducts(products: List<Product>) {
        for (product in products) {
            addSingleProduct(product)
        }
        updateSummary()
    }

    fun setQuantity(item: LineItem, quantity: Double) {
        val products = fields.products
        if (quantity <= 0) {
            summary.intentions.delete(item.uid, item.toParams(), unique)
            products.remove(item)
        } else {
            val i = item.copy(quantity = quantity)
            summary.intentions.update(i.uid, i.toParams(), unique)
            products.update(item) { i }
        }
        updateSummary()
    }

    fun setUnitDiscount(item: LineItem, discount: Double) {
        val products = fields.products
        val i = item.copy(unitDiscount = Monetary(discount))
        summary.intentions.update(i.uid, i.toParams(), unique)
        products.update(item) { i }
        updateSummary()
    }

    fun setCompoundDiscount(item: LineItem, discount: Double) {
        val products = fields.products
        val i = item.copy(compoundDiscount = Monetary(discount))
        summary.intentions.update(i.uid, i.toParams(), unique)
        products.update(item) { i }
        updateSummary()
    }

    fun setGlobalDiscount(amount: Double) {
        fields.discount.setAmount(amount)
        updateSummary()
    }

    fun removeProduct(item: LineItem) {
        fields.products.remove(item)
        summary.intentions.delete(item.uid, item.toParams(), unique)
        updateSummary()
    }

    private companion object {
        val unique = { p1: LineItemParams? -> p1?.data?.asProduct?.uid }

        private fun Product.toLineItemParams(quantity: Double) = LineItemParams(
            details = name,
            data = ref(),
            quantity = quantity,
            unitRate = sellingPrice,
        )

        fun Product.toLineItem(quantity: Double) = LineItem(
            uid = UNSET,
            details = name,
            data = ref(),
            quantity = quantity,
            unitRate = sellingPrice,
            status = TaskStatus.Pending
        )
    }
}