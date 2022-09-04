package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");

        System.out.println();

        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit");

        System.out.println();

        System.out.println("Enter a request:");

        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        while (n != 0) {
            if (isNaturalNumber(n)) {
                boolean isEven = false;
                boolean isOdd = false;
                System.out.println("Properties of " + n);
                if (n % 2 == 0) {
                    isEven = true;
                }
                else {
                    isOdd = true;
                }

                System.out.println("\teven: " + isEven);
                System.out.println("\todd: " + isOdd);
                System.out.println("\tbuzz: " + isBuzzNumber(n));
                System.out.println("\tduck: " + isDuckNumber(n));
                System.out.println("\tpalindromic: " + isPalindromic(n));
            }
            else {
                System.out.println("The first parameter should be a natural number or zero.");
            }

            System.out.println("Enter a request:");
            n = scanner.nextInt();
        }

        System.out.println("Goodbye!");
    }

    public static boolean isPalindromic(long n) {
        String s = String.valueOf(n);

        StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.reverse();

        return s.equals(sb.toString());
    }

    public static boolean isNaturalNumber(long n) {
        return n >= 1;
    }

    public static boolean isBuzzNumber(long n) {
        boolean isDivisibleBy7 = false;
        boolean endsWith7 = false;

        // Check last digit
        long lastDigit = n % 10;
        endsWith7 = lastDigit == 7;

        // Check if divisible by 7
        isDivisibleBy7 = n % 7 == 0;

        if (isDivisibleBy7 || endsWith7) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isDuckNumber(long n) {
        boolean hasZero = false;

        while (n >= 10) {
            long lastDigit = n % 10;
            n = n / 10; // Remove last digit from n

            if (lastDigit == 0) {
                hasZero = true;
                break;
            }
        }

        return hasZero;
    }
}
