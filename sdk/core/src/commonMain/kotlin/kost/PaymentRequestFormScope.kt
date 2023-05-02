@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kost

import bitframe.actions
import flame.FlameApi
import flame.collections.Customers
import identifier.LegalEntity
import kollections.List
import kollections.iEmptyList
import live.mutableLiveOf
import stocker.StockerApi
import stocker.collections.ProductCollection
import stocker.products.Product
import viewmodel.LazyViewModel
import viewmodel.ScopeConfig
import kotlin.js.JsExport

abstract class PaymentRequestFormScope<A, out F : PayRequestForm<*, *, *>>(
    private val config: ScopeConfig<A>
) : LazyViewModel<@UnsafeVariance F>(config) where A : FlameApi, A : StockerApi {

    protected val api = config.api

    val products = mutableLiveOf<ProductCollection?>(null)

    val customers = mutableLiveOf<Customers?>(null)

    val customer = mutableLiveOf<LegalEntity?>(null)

    val intentions get() = ui.value.data?.summary?.intentions ?: iEmptyList()

    override fun deInitialize() {
        customer.value = null
        hideCustomers()
        hideProducts()
        ui.value.data?.summary?.deInitialize(true)
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
        products.value = ProductCollection(config).apply { initialize() }
    }

    fun showCustomers(close: Boolean = DEFAULT_CLOSE_AFTER_CUSTOMERS_SELECTION) {
        val config = config.map { it.customers }.actions {
            single { onAdd { setCustomer(it, close) } }
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

    fun setQuantity(item: LineItem, quantity: Double) = ui.value.data?.setQuantity(item, quantity)

    fun setUnitDiscount(item: LineItem, discount: Double) = ui.value.data?.setUnitDiscount(item, discount)

    fun setCompoundDiscount(item: LineItem, discount: Double) = ui.value.data?.setCompoundDiscount(item, discount)

    fun addQuantity(item: LineItem) = setQuantity(item, (item.quantity + 1))

    fun subQuantity(item: LineItem) = setQuantity(item, (item.quantity - 1))

    fun setGlobalDiscount(amount: Double) = ui.value.data?.setGlobalDiscount(amount)

    fun addProduct(
        product: Product,
        close: Boolean = DEFAULT_CLOSE_AFTER_PRODUCTS_SELECTION
    ) {
        if (close) hideProducts()
        ui.value.data?.addProduct(product)
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

    fun addProducts(
        products: List<Product>,
        close: Boolean = DEFAULT_CLOSE_AFTER_PRODUCTS_SELECTION
    ) {
        if (close) hideProducts()
        ui.value.data?.addProducts(products)
    }

    fun removeProduct(item: LineItem) = ui.value.data?.removeProduct(item)

    private companion object {
        private const val DEFAULT_CLOSE_AFTER_PRODUCTS_SELECTION = false
        private const val DEFAULT_CLOSE_AFTER_CUSTOMERS_SELECTION = false
    }
}