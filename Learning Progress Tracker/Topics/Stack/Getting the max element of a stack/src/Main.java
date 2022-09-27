import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int numberOfCommands = scanner.nextInt();

        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Integer> maxNumbers = new ArrayDeque<>();

        while (scanner.hasNextLine()) {
            String[] command = scanner.nextLine().split(" ");
            switch (command[0]) {
                case "push" -> {
                    int value = Integer.parseInt(command[1]);
                    numbers.push(value);
                    if (maxNumbers.peek() == null || maxNumbers.peek() <= value) {
                        maxNumbers.push(value);
                    }
                }
                case "max" -> System.out.println(maxNumbers.peek());
                case "pop" -> {
                    int poppedValue = numbers.pop();
                    if (maxNumbers.peek() != null && maxNumbers.peek() == poppedValue) {
                        maxNumbers.pop();
                    }
                }
            }
        }
    }
}