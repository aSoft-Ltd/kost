<<<<<<< HEAD
import kost.printToConsole
=======
import kash.Currency
import kost.Address
import kost.Body
import kost.Invoice
import kost.InvoiceHeader
import kost.LineItem
import kost.Subject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import krono.LocalDateTime
>>>>>>> 73a67e5 (Merging with dev-andy)
import kotlin.test.Test

class InvoiceTest {
    @Test
<<<<<<< HEAD
    fun should_print_to_console() {
        val invoice = TestUtils.makeInvoice()
        invoice.printToConsole()
=======
    fun should_serialize() {
        val address = Address.Description("Test Address");
        val invoice = Invoice(
            uid = "<unset>",
            header = InvoiceHeader(
                customer = Subject("<unset>", "John Doe", address),
                currency = Currency.TZS,
                createdOn = LocalDateTime(2021, 1, 1)
            ),
            body = Body(
                LineItem("<unset>", "Keyboard", 1, 2_000_000, "each"),
                LineItem("<unset>", "Computer Maintenance", 2, 10_000_000, "man-days")
            )
        )
        println(Json.encodeToString(invoice))
>>>>>>> 73a67e5 (Merging with dev-andy)
    }
}