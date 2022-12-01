import kost.Address
import kost.Body
import kost.LineItem
import kost.Subject
import kost.Tax

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
    fun makeBody(tax1: Tax = Tax.GENERIC_ZERO, tax2: Tax = tax1): Body {
        return Body(
            LineItem(UNSET, "Test Product 1", 1, 100000, tax = tax1.copy()),
            LineItem(UNSET, "Test Product 2", 2, 200000, tax = tax2.copy()),
            LineItem(UNSET, "Test Product 3", 3, 300000, tax = tax1.copy()),
            LineItem(UNSET, "Test Product 4", 4, 400000, tax = tax2.copy()),
            LineItem(UNSET, "Test Product 5", 5, 500000, tax = tax1.copy())
        )
    }
}
