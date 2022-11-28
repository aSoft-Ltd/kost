import payments.requests.Address
import payments.requests.Sender
import kotlin.test.Test

class SenderKotlinTest {
    @Test
    fun should_instantiate_a_sender() {
        val sender = Sender(uid = "", name = "Test Sender", Address.Description("test address"))
    }
}