import static expect.ExpectBuilders.*;

import identifier.Email;
import identifier.Phone;
import kost.Address;

import org.junit.jupiter.api.Test;

public class AddressJavaTest {
    @Test
    public void should_instantiate_intuitively() {
        var address1 = new Address.LocationWithContacts(
                "Tanzania",
                "Dar Es Salaam",
                "Sinza",
                Phone.of(711111122),
                Email.of("andy@lamax.net")
        );

        var address2 = new Address.Contacts(
                Phone.of("255711111122"),
                Email.of("andy@lamax.net")
        );

        expect(address1.getPhone().toString()).toBe(address2.getPhone().toString());
    }
}
