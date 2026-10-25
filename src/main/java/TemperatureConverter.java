import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        System.out.println("Temperature Converter");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Celsius: ");

        double celsius = scanner.nextDouble();
        double fahrenheit = celsius * (9.0 / 5) + 32;

        System.out.println(celsius + "°C = " + fahrenheit + "°F");

        scanner.close();
    }
}
