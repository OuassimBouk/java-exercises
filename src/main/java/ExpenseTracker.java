import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ExpenseTracker {
    public static void main(String[] args) {
        IO.println("=====EXPENSE TRACKER=====");


        BigDecimal totalAmount = BigDecimal.ZERO;
        Map<String, BigDecimal> categories = new HashMap<>();

        while (true) {
            String amountInput = IO.readln("Amount: ").strip();
            String categoryInput = IO.readln("Category: ").strip();
            String choiseInput = IO.readln("Press enter to add another expense or type done and enter to get a summary: ").strip();

            BigDecimal amount = new BigDecimal(amountInput);
            totalAmount = totalAmount.add(amount);
            if (categories.containsKey(categoryInput)) {
                BigDecimal currentCategoryAmount = new BigDecimal(String.valueOf(categories.get(categoryInput)));
                categories.put(categoryInput, amount.add(currentCategoryAmount));
            } else {
                categories.put(categoryInput, amount);
            }

            if (choiseInput.equals("done")) {
                IO.println("=====SUMMARY=====");
                IO.println("Total spent: " + totalAmount);
                for (Map.Entry<String, BigDecimal> entry : categories.entrySet()) {
                    IO.println(entry.getKey() + ": " + entry.getValue());
                }
                return;
            }
        }
    }
}
