import kash.Currency
import kash.Monetary
import kommerce.ProductRef
import kost.Address
import kost.Body
import kost.Invoice
import kost.InvoiceHeader
import kost.LineItem
import kost.Subject
import kost.Tax
import krono.Now
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmOverloads

object TestUtils {
    const val UNSET = "<unset>"

    @JvmStatic
    fun makeAddress(): Address {
        return Address.Description("Test Address")
    }

    @JvmStatic
    fun makeSubject(address: Address) = Subject(UNSET, "Test Subject", address)

    @JvmStatic
    @JvmOverloads
    fun makeBody(tax1: Tax = Tax.GENERIC_ZERO, tax2: Tax = tax1) = Body(
        LineItem(UNSET, data = ProductRef(name = "Computer Keyboard", uid = ""), quantity = 1.0, unitRate = Monetary(2_000_000), unit = "each"),
        LineItem(UNSET, data = ProductRef(name = "Call of Duty: Warzone", uid = ""), quantity = 2.0, unitRate = Monetary(10_000_000), unit = "man-days"),
        LineItem(UNSET, data = ProductRef(name = "HP Deskject Printer", uid = ""), quantity = 1.0, unitRate = Monetary(100000), tax = tax1.copy()),
        LineItem(UNSET, data = ProductRef(name = "Samsung QLed 55 TV", uid = ""), quantity = 2.0, unitRate = Monetary(200000), tax = tax2.copy()),
        LineItem(UNSET, data = ProductRef(name = "Samsung Galaxy Note 9", uid = ""), quantity = 3.0, unitRate = Monetary(300000), tax = tax1.copy()),
        LineItem(UNSET, data = ProductRef(name = "Mitsubish RVR 2002", uid = ""), quantity = 4.0, unitRate = Monetary(400000), tax = tax2.copy())
    )

    fun makeInvoice(address: Address = Address.Description("Test Address")) = Invoice(
        uid = "<unset>",
        header = InvoiceHeader(
            customer = Subject("<unset>", "John Doe", address),
            currency = Currency.TZS,
            createdOn = Now()
        ),
        body = makeBody()
    )
}
