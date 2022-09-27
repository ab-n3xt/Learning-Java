import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here

        int numberOfInstructions = scanner.nextInt();

        int sum = 0;

        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();

            if (number % 6 == 0) sum += number;
        }

        System.out.println(sum);
    }
}