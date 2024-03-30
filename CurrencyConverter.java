import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Scanner;

public class CurrencyConverter {
    private static final double USD_TO_INR = 75.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Currency Converter");
        System.out.println("Enter the amount:");
        double amount = scanner.nextDouble();
        System.out.println("\n Select the currency for conversion");
        System.out.println("1. USD to INR");
        System.out.println("2. INR to USD");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        Currency fromCurrency, toCurrency;
        switch(choice) {
            case 1:
                fromCurrency =  Currency.getInstance("USD");
                toCurrency = Currency.getInstance("INR");
                break;
            case 2:
                fromCurrency = Currency.getInstance("INR");
                toCurrency = Currency.getInstance("USD");
                break;
            default:
                System.out.println("Invalid input");
                scanner.close();
                return;
        }
        double convertedAmount =  convertCurrency(amount, fromCurrency, toCurrency);
        System.out.println("Amount after conversion:"+convertedAmount+" "+ toCurrency.getCurrencyCode());
        scanner.close();
    }

    public static double convertCurrency(double amount, Currency fromCurrency, Currency toCurrency){
        double exchangeRate = getExchangeRate(fromCurrency, toCurrency);
        BigDecimal result = BigDecimal.valueOf(amount * exchangeRate);
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    private static double getExchangeRate(Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency.getCurrencyCode().equals("USD") && toCurrency.getCurrencyCode().equals("INR")) {
            return USD_TO_INR;
        } else if (fromCurrency.getCurrencyCode().equals("INR") && toCurrency.getCurrencyCode().equals("USD")) {
            return 1 / USD_TO_INR;
        } else {
            throw new IllegalArgumentException("Exchange rate not available for the provided currencies.");
        }
    }
}
