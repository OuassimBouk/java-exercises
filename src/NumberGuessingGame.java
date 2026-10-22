import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberGuessingGame {
    public static void main(String[] arg) {
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;

        System.out.println("Number guessing game! " + secretNumber);

        Scanner scanner = new Scanner(System.in);
        int guessCount = 0;
        while (true) {

            if (guessCount == 0) {
                System.out.print("Guess a number from 1 to 100: ");
            } else {
                System.out.print("Take another guess. The number is from 1 to 100: ");
            }

            int numberGuessed = 0;
            try {
                numberGuessed = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Discard invalid input
                continue;
            }

            guessCount++;

            if (numberGuessed < 1 || numberGuessed > 100) {
                System.out.println(numberGuessed + " is outside of the guessing range. Please try again.");
            } else if (numberGuessed == secretNumber) {
                System.out.println("You are correct! The number was: " + numberGuessed);
                String guessName = "guesses";
                if (guessCount == 1) {
                    guessName = "guess";
                }
                System.out.println("It took you " + guessCount + " " + guessName + " to get the correct number!");
                break;
            } else {
                if (numberGuessed < secretNumber) {
                    System.out.println("That is incorrect. " + numberGuessed + " is lower than the correct answer.");
                } else {
                    System.out.println("That is incorrect. " + numberGuessed + " is higher than the correct answer.");
                }
            }
        }

    }
}
