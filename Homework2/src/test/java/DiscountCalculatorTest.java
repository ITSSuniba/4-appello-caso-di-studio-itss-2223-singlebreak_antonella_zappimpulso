import org.example.DiscountCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {

    @Test
    public void testNoDiscountApplicable() {
        double price = -10.0;
        int quantity = -4;
        double expectedDiscountedPrice = 0.0; // Lo sconto non è applicabile, quindi il prezzo scontato è 0.0

        double actualDiscountedPrice = DiscountCalculator.calculateProductDiscount(price, quantity);

        assertEquals(expectedDiscountedPrice, actualDiscountedPrice, 0.001);
    }

    @Test
    public void testDiscountIsApplicable() {
        double price = 10.0;
        int quantity = 5;
        double expectedDiscountedPrice = 42.5; // price * quantity * (1 - 0.15) = 10 * 5 * 0.95 = 47.5

        double actualDiscountedPrice = DiscountCalculator.calculateProductDiscount(price, quantity);

        assertEquals(expectedDiscountedPrice, actualDiscountedPrice, 0.001);
    }

    @Test
    public void testDiscountIsApplicablecase2() {
        double price = 10.0;
        int quantity = 12;
        double expectedDiscountedPrice = 108.0; // price * quantity * (1 - 0.05) = 10 * 5 * 0.95 = 47.5

        double actualDiscountedPrice = DiscountCalculator.calculateProductDiscount(price, quantity);

        assertEquals(expectedDiscountedPrice, actualDiscountedPrice, 0.001);
    }
}
