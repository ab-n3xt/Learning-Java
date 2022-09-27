import java.util.ArrayDeque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        ArrayDeque<Integer> q = new ArrayDeque<>();
        Scanner scanner = new Scanner(System.in);

        int numberOfInputs = scanner.nextInt();

        while (scanner.hasNextInt()) {
            int num = scanner.nextInt();

            if (num % 2 == 0) {
                q.addFirst(num);
            }
            else {
                q.addLast(num);
            }
        }

        for (int n:
             q) {
            System.out.println(n);
        }
    }
}