
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvoiceCalculatorTest {

    // TEST 1: VAT calculation and rounding

    @ParameterizedTest
    @CsvSource({
            "100.00, 21.00",
            "9.99, 2.10",
            "0.50, 0.11",
            "0.00, 0.00",
            "50.00, 10.50",
            "0.01, 0.00",
            "0.03, 0.01"
    })
    void calculateVatShouldReturnCorrectAmount(
            String subtotal,
            String expectedVat
    ) {
        // Arrange
        BigDecimal input = new BigDecimal(subtotal);
        BigDecimal expected = new BigDecimal(expectedVat);

        // Act
        BigDecimal actual = InvoiceCalculator.calculateVat(input);

        // Assert
        assertEquals(expected, actual);
    }

    // TEST 2: Subtotal calculation

    @ParameterizedTest
    @CsvSource({
            "19.99, 3, 59.97",
            "50.00, 2, 100.00",
            "0.00, 5, 0.00",
            "0.10, 3, 0.30",
            "99.99, 1, 99.99",
            "5, 2, 10.00"
    })
    void calculateSubtotalShouldMultiplyCorrectly(
            String unitPrice,
            int quantity,
            String expectedSubtotal
    ) {
        // Arrange
        BigDecimal price = new BigDecimal(unitPrice);
        BigDecimal expected = new BigDecimal(expectedSubtotal);

        // Act
        BigDecimal actual = InvoiceCalculator.calculateSubtotal(
                price, quantity
        );

        // Assert
        assertEquals(expected, actual);
    }

    // TEST 3: Total including VAT

    @ParameterizedTest
    @CsvSource({
            "100.00, 21.00, 121.00",
            "9.99, 2.10, 12.09",
            "0.00, 0.00, 0.00",
            "0.50, 0.11, 0.61",
            "59.97, 12.59, 72.56"
    })
    void calculateTotalShouldAddVatCorrectly(
            String subtotal,
            String vat,
            String expectedTotal
    ) {
        // Arrange
        BigDecimal subtotalAmount = new BigDecimal(subtotal);
        BigDecimal vatAmount = new BigDecimal(vat);
        BigDecimal expected = new BigDecimal(expectedTotal);

        // Act
        BigDecimal actual = InvoiceCalculator.calculateTotalIncludingVat(
                subtotalAmount, vatAmount
        );

        // Assert
        assertEquals(expected, actual);
    }
}
