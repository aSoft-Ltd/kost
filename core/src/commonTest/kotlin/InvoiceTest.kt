import kost.printToConsole
import kotlin.test.Test

class InvoiceTest {
    @Test
    fun should_print_to_console() {
        val invoice = TestUtils.makeInvoice()
        invoice.printToConsole()
    }
}