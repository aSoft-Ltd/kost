import identifier.Email;
import identifier.Phone;
import kost.Address;
import org.junit.jupiter.api.Test;

public class SenderJavaTest {

    @Test
    public void should_instantiate_a_sender_correctly() {
        var address1 = new Address.LocationWithContacts(
                "Tanzania",
                "Dar Es Salaam",
                "Bunju",
                Phone.of("254711111122"),
                Email.of("andy@lamax.net")
        );
        var address2 = new Address.Description("Michigan");
    }
}
