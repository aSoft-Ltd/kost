import kash.Currency
import kost.Address
import kost.Body
import kost.Invoice
import kost.InvoiceHeader
import kost.LineItem
import kost.Subject
import kost.Tax
import krono.LocalDateTime
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
        LineItem(UNSET, "Computer Keyboard", 1, 2_000_000, "each"),
        LineItem(UNSET, "Call of Duty: Warzone", 2, 10_000_000, "man-days"),
        LineItem(UNSET, "HP Deskject Printer", 1, 100000, tax = tax1.copy()),
        LineItem(UNSET, "Samsung QLed 55 TV", 2, 200000, tax = tax2.copy()),
        LineItem(UNSET, "Samsung Galaxy Note 9", 3, 300000, tax = tax1.copy()),
        LineItem(UNSET, "Mitsubish RVR 2002", 4, 400000, tax = tax2.copy())
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
