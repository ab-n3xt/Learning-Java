import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();

        Deque<Integer> d = new ArrayDeque<>(size);

        while (scanner.hasNextInt()) {
            d.push(scanner.nextInt());
        }

        while (d.peek() != null) {
            System.out.println(d.pop());
        }
    }
}