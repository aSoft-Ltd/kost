import kash.Currency
import kash.Money
import kost.Address
import kost.Body
import kost.Invoice
import kost.InvoiceHeader
import kost.LineItem
import kost.Subject
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import krono.Now
import kommerce.ProductRef
import kotlin.test.Test

class InvoiceSerializationTest {

    @Test
    fun should_serialize() {
        val address = Address.Description("Test Address")
        val invoice = Invoice(
            uid = "<unset>",
            header = InvoiceHeader(
                customer = Subject("<unset>", "John Doe", address),
                currency = Currency.TZS,
                createdOn = Now()
            ),
            body = Body(
                LineItem("<unset>", data = ProductRef("unset", "Keyboard"), quantity = 1, unitRate = Money(2_000_000), unit = "each"),
                LineItem("<unset>", data = ProductRef("unset", "Keyboard"), quantity = 2, unitRate = Money(10_000_000), unit = "man-days")
            )
        )
        println(Json.encodeToString(invoice))
    }
}