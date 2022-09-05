package numbers;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Amazing Numbers!");

        System.out.println();

        printInstructions();

        System.out.println();

        boolean runningProgram = true;

        while (runningProgram) {
            System.out.println("Enter a request:");
            String input = scanner.nextLine();
            String[] params = input.split(" ");

            if (params.length == 1 && params[0].equals("")) {
                printInstructions();
            }
            else if (params.length == 1) {
                long n;

                try {
                    n = Long.parseLong(params[0]);
                }
                catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }

                if (isNaturalNumber(n)) {
                    System.out.println("Properties of " + n);
                    System.out.println("\teven: " + isEvenNumber(n));
                    System.out.println("\todd: " + !isEvenNumber(n));
                    System.out.println("\tbuzz: " + isBuzzNumber(n));
                    System.out.println("\tduck: " + isDuckNumber(n));
                    System.out.println("\tpalindromic: " + isPalindromic(n));
                    System.out.println("\tgapful: " + isGapfulNumber(n));
                }
                else if (n == 0) {
                    runningProgram = false;
                }
                else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            else if (params.length == 2) {
                long n, n2;

                try {
                    n = Long.parseLong(params[0]);
                }
                catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                    continue;
                }

                try {
                    n2 = Long.parseLong(params[1]);
                }
                catch (NumberFormatException e) {
                    System.out.println("The second parameter should be a natural number.");
                    continue;
                }

                if (isNaturalNumber(n)) {
                    if (isNaturalNumber(n2)) {
                        long count = 0;
                        while (count < n2) {
                            StringBuilder sentence = new StringBuilder(n + " is ");

                            if (isBuzzNumber(n)) sentence.append("buzz, ");
                            if (isDuckNumber(n)) sentence.append("duck, ");
                            if (isPalindromic(n)) sentence.append("palindromic, ");
                            if (isGapfulNumber(n)) sentence.append("gapful, ");

                            if (isEvenNumber(n)) sentence.append("even");
                            else sentence.append("odd");

                            System.out.println(sentence);

                            // Loop ended, increment variables
                            count++;
                            n++;
                        }
                    }
                    else {
                        System.out.println("The second parameter should be a natural number.");
                    }
                }
                else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            else {
                System.out.println("Too many parameters received!");
            }
        }
        System.out.println("Goodbye!");
    }


    public static boolean isEvenNumber(long n) {
        return n % 2 == 0;
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

        return isDivisibleBy7 || endsWith7;
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

    public static boolean isGapfulNumber(long n) {
        // Check if at least 3 digits
        if (n < 100) {
            return false;
        }
        long firstDigit = Character.digit(String.valueOf(n).charAt(0), 10);
        long lastDigit = n % 10;

        long gapNumber = firstDigit * 10 + lastDigit;

        return n % gapNumber == 0;
    }

    public static void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }
}
