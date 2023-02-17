@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bitframe.actions
import flame.FlameApi
import flame.collections.Customers
import identifier.LegalEntity
import identifier.UNSET
import kash.Monetary
import kollections.List
import kollections.iMutableListOf
import kost.params.LineItemParams
import live.mutableLiveOf
import presenters.Form
import stocker.StockerApi
import stocker.collections.Products
import stocker.products.Product
import viewmodel.LazyViewModel
import viewmodel.ScopeConfig
import kotlin.js.JsExport

abstract class PaymentRequestFormScope<A, F : PaymentRequestFields>(
    private val config: ScopeConfig<A>
) : LazyViewModel<Form<F, Any?, Any>>(config) where A : FlameApi, A : StockerApi {

    protected val api = config.api

    val products = mutableLiveOf<Products?>(null)

    val customers = mutableLiveOf<Customers?>(null)

    val customer = mutableLiveOf<LegalEntity?>(null)

    val summary = LineItems(config)

    val intentions = iMutableListOf<Intention<String, LineItemParams>>()

    override fun deInitialize() {
        customer.value = null
        hideCustomers()
        hideProducts()
        summary.deInitialize(true)
        intentions.clear()
        super.deInitialize()
    }

    /**
     * Shows list of available products to be selected from
     * @param close - if set to true, will automatically hide this list
     */
    fun showProducts(close: Boolean = DEFAULT_CLOSE_AFTER_PRODUCTS_SELECTION) {
        val config = config.map { it.products }.actions {
            single { onAdd { addProduct(it, close) } }
            multi { onAddAll { addProducts(it) } }
        }
        products.value = Products(config).apply { initialize() }
    }

    fun showCustomers(close: Boolean = DEFAULT_CLOSE_AFTER_CUSTOMERS_SELECTION) {
        val config = config.map { it.customers }.actions {
            single { onAdd { setCustomer(it) } }
        }
        customers.value = Customers(config).apply { initialize() }
    }

    fun hideProducts() {
        products.value?.deInitialize(true)
        products.value = null
    }

    fun hideCustomers() {
        customers.value?.deInitialize(true)
        customers.value = null
    }

    private fun updateSummary() {
        val fields = ui.value.data?.fields ?: return
        val items = fields.products.data.value.output
        val discount = fields.discount.data.value.output ?: Monetary(0)
        summary.update(ItemizedCalculable(items, discount))
        logger.debug("""----------Intentions-------------""")
        intentions.forEach {
            println(it.prettyString())
        }
        logger.debug("""----------Intentions-------------""")
    }

    fun setQuantity(item: LineItem, quantity: Double) {
        val pList = ui.value.data?.fields?.products ?: return
        if (quantity <= 0) {
            intentions.delete(item.uid, item.toParams(), unique)
            pList.remove(item)
        } else {
            val i = item.copy(quantity = quantity)
            intentions.update(i.uid, i.toParams(), unique)
            pList.update(item) { i }
        }
        updateSummary()
    }

    fun setUnitDiscount(item: LineItem, discount: Double) {
        val pList = ui.value.data?.fields?.products ?: return
        val i = item.copy(unitDiscount = Monetary(discount))
        intentions.update(i.uid, i.toParams(), unique)
        pList.update(item) { i }
        updateSummary()
    }

    fun setCompoundDiscount(item: LineItem, discount: Double) {
        val pList = ui.value.data?.fields?.products ?: return
        val i = item.copy(compoundDiscount = Monetary(discount))
        intentions.update(i.uid, i.toParams(), unique)
        pList.update(item) { i }
        updateSummary()
    }

    fun addQuantity(item: LineItem) = setQuantity(item, (item.quantity + 1))

    fun subQuantity(item: LineItem) = setQuantity(item, (item.quantity - 1))

    fun setOrderDiscount(amount: Double) {
        ui.value.data?.fields?.discount?.setAmount(amount)
        updateSummary()
    }

    private val unique = { p1: LineItemParams? -> p1?.data?.asProduct?.uid }

    private fun addSingleProduct(p: Product) {
        val pList = ui.value.data?.fields?.products ?: return
        val item = pList.data.value.output.find { it.data.uid == p.uid }
        if (item == null) {
            val quantity = 1.0
            pList.add(p.toLineItem(quantity))
            intentions.create(p.toLineItemParams(quantity), unique)
        } else {
            val i = item.copy(quantity = item.quantity + 1.0)
            pList.update(item) { i }
            intentions.update(i.uid, i.toParams(), unique)
        }
    }

    fun setCustomer(c: LegalEntity, close: Boolean = DEFAULT_CLOSE_AFTER_CUSTOMERS_SELECTION) {
        customer.value = c
        ui.value.data?.fields?.customerId?.set(c.uid)
        if (close) hideCustomers()
    }

    fun removeCustomer() {
        customer.value = null
        ui.value.data?.fields?.customerId?.clear()
    }

    fun addProduct(
        product: Product,
        close: Boolean = DEFAULT_CLOSE_AFTER_PRODUCTS_SELECTION
    ) {
        if (close) hideProducts()
        addSingleProduct(product)
        updateSummary()
    }

    fun addProducts(
        products: List<Product>,
        close: Boolean = DEFAULT_CLOSE_AFTER_PRODUCTS_SELECTION
    ) {
        if (close) hideProducts()
        for (product in products) addSingleProduct(product)
        updateSummary()
    }

    fun removeProduct(item: LineItem) {
        ui.value.data?.fields?.products?.remove(item)
        intentions.delete(item.uid, item.toParams(), unique)
        updateSummary()
    }

    private companion object {
        private const val DEFAULT_CLOSE_AFTER_PRODUCTS_SELECTION = false
        private const val DEFAULT_CLOSE_AFTER_CUSTOMERS_SELECTION = false

        private fun Product.toLineItemParams(quantity: Double) = LineItemParams(
            details = name,
            data = ref(),
            quantity = quantity,
            unitRate = sellingPrice
        )

        private fun Product.toLineItem(quantity: Double) = LineItem(
            uid = UNSET,
            details = name,
            data = ref(),
            quantity = quantity,
            unitRate = sellingPrice
        )
    }
}