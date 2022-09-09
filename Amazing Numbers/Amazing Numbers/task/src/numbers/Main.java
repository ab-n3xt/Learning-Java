package numbers;

import java.util.ArrayList;
import java.util.Arrays;
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
                    System.out.println("\tspy: " + isSpyNumber(n));
                    System.out.println("\tsunny: " + isSunnyNumber(n));
                    System.out.println("\tsquare: " + isPerfectSquare(n));
                    System.out.println("\tjumping: " + isJumpingNumber(n));
                    System.out.println("\thappy: " + isHappyNumber(n));
                    System.out.println("\tsad: " + isSadNumber(n));
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
                            printProperties(n);

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
            else if (params.length >= 3) {

                long n, n2;
                ArrayList<NumberType> propertiesToFind = new ArrayList<>();

                // Parse two numbers

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

                // Parse property / properties
                String[] propertyArray = Arrays.copyOfRange(params, 2, params.length);

                // Variables to keep track of how many incorrect arguments are given
                // This is to help with providing the correct error message to the user
                boolean incorrectParams = false;
                StringBuilder sb = new StringBuilder();

                boolean conflictingProperties = false;

                // Loop through each argument and check if they are a valid NumberType enum
                // For every incorrect one, throw an IllegalArgumentException
                for (String p : propertyArray) {
                    try {
                        String properP = p.replace("-", "NOT").toUpperCase();
                        NumberType property = NumberType.valueOf(properP);
                        // Check current list and see if there are any conflicts

                        for (NumberType nt : propertiesToFind) {
                            if (NumberType.isConflicting(nt, property)) {
                                NumberType.reportConflict(nt, property);
                                conflictingProperties = true;
                            }
                        }

                        if (conflictingProperties) break;

                        // If passed the for-loop, there is no conflicts currently
                        propertiesToFind.add(property);
                    }
                    catch (IllegalArgumentException e) {
                        if (sb.isEmpty()) sb.append(p.toUpperCase());
                        else sb.append(" ").append(p.toUpperCase());
                        incorrectParams = true;
                    }
                }

                // If any incorrect parameters are detected, go here
                // Then go back to the start
                if (incorrectParams) {
                    String[] illegalParameters = sb.toString().split(" ");
                    if (illegalParameters.length == 1) {
                        System.out.printf("The property %s is wrong.%n", Arrays.deepToString(illegalParameters));
                    }
                    else {
                        System.out.printf("The properties %s are wrong.%n", Arrays.deepToString(illegalParameters));
                    }
                    System.out.println("Available properties: " + Arrays.deepToString(NumberType.values()));

                    continue;
                }
                else if (conflictingProperties) {
                    continue;
                }

                long count = 0;

                for (long i = n; count < n2; i++) {
                    boolean hasAllProperties = true;

                    for (NumberType nt : propertiesToFind) {
                        if (!NumberType.isValid(nt, i)) {
                            hasAllProperties = false;
                            break;
                        }
                    }

                    if (hasAllProperties) {
                        printProperties(i);
                        count++;
                    }
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

    public static boolean isSpyNumber(long n) {
        char[] charDigits = String.valueOf(n).toCharArray();

        long sum = 0;
        long product = 1;

        for (char c : charDigits) {
            sum += Long.parseLong(String.valueOf(c));
            product *= Long.parseLong(String.valueOf(c));
        }

        return sum == product;
    }

    public static boolean isSunnyNumber(long n) {
        return isPerfectSquare(n + 1);
    }

    public static boolean isPerfectSquare(long n) {
        return (Math.sqrt(n) % 1.0) == 0.0;
    }

    public static boolean isJumpingNumber(long n) {
        if (n < 10) {
            return true;
        }

        char[] digits = Long.toString(n).toCharArray();
        for (int i = 0; i < digits.length - 1; i++) {
            long d1 = digits[i];
            long d2 = digits[i+1];

            // The difference between digits is greater than 1
            if (Math.abs(d1 - d2) != 1) {
                return false;
            }
        }

        return true;
    }

    public static boolean isHappyNumber(long n) {
        long originalNumber = n;

        ArrayList<Long> pastSums = new ArrayList<>();

        while (true) {
            long sum = 0;

            while (n != 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }

            if (sum == 1) {
                return true;
            }
            else {
                // Look through past results
                // If arraylist contains past sums, then we are in an infinite loop
                if (!pastSums.contains(sum)) pastSums.add(sum);
                else return false;
            }

            n = sum;
        }
    }

    public static boolean isSadNumber(long n) {
        return !isHappyNumber(n);
    }

    public static void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }

    public static void printProperties(long n) {
        StringBuilder sentence = new StringBuilder(n + " is ");

        if (isBuzzNumber(n)) sentence.append("buzz, ");
        if (isDuckNumber(n)) sentence.append("duck, ");
        if (isPalindromic(n)) sentence.append("palindromic, ");
        if (isGapfulNumber(n)) sentence.append("gapful, ");
        if (isSpyNumber(n)) sentence.append("spy, ");
        if (isSunnyNumber(n)) sentence.append("sunny, ");
        if (isPerfectSquare(n)) sentence.append("square, ");
        if (isJumpingNumber(n)) sentence.append("jumping, ");
        if (isHappyNumber(n)) sentence.append("happy, ");
        if (isSadNumber(n)) sentence.append("sad, ");

        if (isEvenNumber(n)) sentence.append("even");
        else sentence.append("odd");

        System.out.println(sentence);
    }

    public enum NumberType {

        EVEN, NOTEVEN,
        ODD, NOTODD,
        BUZZ, NOTBUZZ,
        DUCK, NOTDUCK,
        PALINDROMIC, NOTPALINDROMIC,
        GAPFUL, NOTGAPFUL,
        SPY, NOTSPY,
        SQUARE, NOTSQUARE,
        SUNNY, NOTSUNNY,
        JUMPING, NOTJUMPING,
        HAPPY, NOTHAPPY,
        SAD, NOTSAD;

        public String toString() {
            if (this.name().startsWith("NOT")) {
                return this.name().replaceFirst("NOT", "-");
            }
            else {
                return this.name();
            }
        }

        public static boolean isValid(NumberType nt, long n) {
            return switch (nt) {
                case EVEN, NOTODD -> isEvenNumber(n);
                case ODD, NOTEVEN -> !isEvenNumber(n);
                case BUZZ -> isBuzzNumber(n);
                case NOTBUZZ -> !isBuzzNumber(n);
                case DUCK -> isDuckNumber(n);
                case NOTDUCK -> !isDuckNumber(n);
                case SPY -> isSpyNumber(n);
                case NOTSPY -> !isSpyNumber(n);
                case PALINDROMIC -> isPalindromic(n);
                case NOTPALINDROMIC -> !isPalindromic(n);
                case GAPFUL -> isGapfulNumber(n);
                case NOTGAPFUL -> !isGapfulNumber(n);
                case SQUARE -> isPerfectSquare(n);
                case NOTSQUARE -> !isPerfectSquare(n);
                case SUNNY -> isSunnyNumber(n);
                case NOTSUNNY -> !isSunnyNumber(n);
                case JUMPING -> isJumpingNumber(n);
                case NOTJUMPING -> !isJumpingNumber(n);
                case HAPPY, NOTSAD -> isHappyNumber(n);
                case SAD, NOTHAPPY -> isSadNumber(n);
            };
        }

        public static boolean isConflicting(NumberType p, NumberType p2) {
            return switch (p) {
                case EVEN -> p2 == ODD || p2 == NOTODD || p2 == NOTEVEN;
                case NOTEVEN -> p2 == ODD || p2 == NOTODD || p2 == EVEN;
                case ODD -> p2 == EVEN || p2 == NOTEVEN || p2 == NOTODD;
                case NOTODD -> p2 == EVEN || p2 == NOTEVEN || p2 == ODD;
                case DUCK -> p2 == SPY || p2 == NOTSPY || p2 == NOTDUCK;
                case NOTDUCK -> p2 == NOTSPY || p2 == DUCK;
                case SPY -> p2 == DUCK || p2 == NOTSPY;
                case NOTSPY -> p2 == DUCK || p2 == NOTDUCK || p2 == SPY;
                case SUNNY -> p2 == SQUARE || p2 == NOTSQUARE || p2 == NOTSUNNY;
                case NOTSUNNY -> p2 == SQUARE || p2 == SUNNY;
                case SQUARE -> p2 == SUNNY || p2 == NOTSUNNY || p2 == NOTSQUARE;
                case NOTSQUARE -> p2 == SUNNY || p2 == SQUARE;
                case HAPPY -> p2 == SAD || p2 == NOTSAD || p2 == NOTHAPPY;
                case NOTHAPPY -> p2 == SAD || p2 == NOTSAD || p2 == HAPPY;
                case SAD -> p2 == HAPPY || p2 == NOTHAPPY || p2 == NOTSAD;
                case NOTSAD -> p2 == HAPPY || p2 == SAD;
                default -> false;
            };
        }

        public static void reportConflict(NumberType p, NumberType p2) {
            System.out.println("The request contains mutually exclusive properties:");

            System.out.println("[" + p + ", " + p2 + "]");

            System.out.println("There are no numbers with these properties.");
        }
    }
}

