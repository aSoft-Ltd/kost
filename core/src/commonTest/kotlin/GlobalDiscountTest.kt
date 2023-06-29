import expect.expect
import expect.toBe
import kash.Monetary
import kash.Zero
import kommander.expect
import kost.GlobalDiscount
import kost.LineItemDiscount
import kost.discountOf
import kotlin.test.Test

class GlobalDiscountTest {
    @Test
    fun cost_after_discount_should_be_less_than_cost_before_discount() {
        val costBefore = Monetary(1000)
        val discount = discountOf(costBefore, Zero, 1.0, Monetary(200))
        expect<LineItemDiscount>(discount).toBe<GlobalDiscount>()
        expect(discount.costBefore.amountAsInt).toBe(1000)
        expect(discount.costAfter.amountAsInt).toBe(800)
    }
}