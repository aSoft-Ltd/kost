import kost.*;
import kash.Currency;

import krono.InstantKxBuildersKt;
import krono.LocalDateTimeConstructorsKt;
import krono.TimeZones;
import org.junit.jupiter.api.Test;

import static expect.ExpectBuilders.*;

public class InvoiceJavaTest {

    @Test
    public void body_should_calculate_tax_appropriately() {
        var SRS = new TaxAgency("South Africa Revenue Service");
        var body1 = TestUtils.makeBody(new Tax("GST", 10, SRS));
        expect(body1.getCostBeforeTax().getAmountAsLong()).toBe(5500000L);
        expect(body1.getCostAfterTax().getAmountAsLong()).toBe(6050000L);

        var body2 = TestUtils.makeBody(Tax.GENERIC_ZERO);
        expect(body2.getCostBeforeTax().getAmountAsLong()).toBe(5500000L);
        expect(body2.getCostBeforeTax()).toBe(body2.getCostAfterTax());

        var body3 = TestUtils.makeBody(new Tax("GST", 10, SRS), new Tax("VAT", 15, SRS));
        System.out.println(body3.getTaxRates());
    }

    @Test
    public void should_create_a_minimalistic_invoice() {
        var address = TestUtils.makeAddress();
        var customer = TestUtils.makeSubject(address);
        var createdOn = InstantKxBuildersKt.Now();
        var header = new InvoiceHeader(customer, Currency.TZS.INSTANCE, createdOn);
        var body = TestUtils.makeBody();
        var invoice = new Invoice(TestUtils.UNSET, header, body);
        System.out.println(invoice);
    }
}
