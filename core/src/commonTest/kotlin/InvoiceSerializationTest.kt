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
import krono.Now
import kotlin.test.Test

class InvoiceSerializationTest {
    @Test
    fun should_serialize() {
        val address = Address.Description("Test Address");
        val invoice = Invoice(
            uid = "<unset>",
            header = InvoiceHeader(
                customer = Subject("<unset>", "John Doe", address),
                currency = Currency.TZS,
                createdOn = Now()
            ),
            body = Body(
                LineItem("<unset>", "Keyboard", 1, 2_000_000, "each"),
                LineItem("<unset>", "Computer Maintenance", 2, 10_000_000, "man-days")
            )
        )
        println(Json.encodeToString(invoice))
    }
}