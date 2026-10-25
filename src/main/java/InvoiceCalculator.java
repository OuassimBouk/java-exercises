import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvoiceCalculator {
    private static final BigDecimal VAT_RATE = new BigDecimal("0.21");

    static BigDecimal readValidPrice() {
        while (true) {
            String input = IO.readln("Enter product price: ").strip();

            try {
                BigDecimal price = new BigDecimal(input);

                if (price.compareTo(BigDecimal.ZERO) < 0) {
                    IO.println("Price cannot be negative.");
                    continue;
                }

                if (price.scale() > 2) {
                    IO.println("Price can have at most 2 decimal places.");
                    continue;
                }

                return price.setScale(2, RoundingMode.HALF_UP);

            } catch (NumberFormatException e) {
                IO.println("Invalid price. Please enter a number.");
            }
        }
    }

    static int readValidQuantity() {
        while (true) {
            String input = IO.readln("Enter product quantity: ").strip();

            try {
                int quantity = Integer.parseInt(input);

                if (quantity < 1) {
                    IO.println("Quantity cannot be less than 1.");
                    continue;
                }

                return quantity;

            } catch (NumberFormatException e) {
                IO.println("Invalid quantity amount. Please enter a number.");
            }
        }
    }

    static BigDecimal calculateSubtotal(BigDecimal price, int quantity) {
        return (price.multiply(BigDecimal.valueOf(quantity))).setScale(2, RoundingMode.HALF_UP);
    }

    static BigDecimal calculateVat(BigDecimal subtotal) {
        return (subtotal.multiply(VAT_RATE)).setScale(2, RoundingMode.HALF_UP);
    }

    static BigDecimal calculateTotalIncludingVat(BigDecimal subtotal, BigDecimal vat) {
        return subtotal.add(vat);
    }

    static void printInvoice(String product, BigDecimal price, int quantity, BigDecimal subtotal, BigDecimal vat, BigDecimal total) {
        IO.println("========== INVOICE ==========");
        IO.println("Product: " + product);
        IO.println("Unit price: " + price);
        IO.println("Quantity: " + quantity);
        IO.println("-----------------------------");
        IO.println("Subtotal: " + subtotal);
        IO.println("VAT (21%): " + vat);
        IO.println("Total: " + total);
        IO.println("=============================");
    }

    public static void main(String[] args) {
        String product = IO.readln("Product name: ");
        BigDecimal price = readValidPrice();
        int quantity = readValidQuantity();

        BigDecimal subtotal = calculateSubtotal(price, quantity);
        BigDecimal vat = calculateVat(subtotal);
        BigDecimal totalIncludingVat = calculateTotalIncludingVat(subtotal, vat);

        printInvoice(product, price, quantity, subtotal, vat, totalIncludingVat);
    }
}
