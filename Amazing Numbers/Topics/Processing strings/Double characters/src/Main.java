import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();

        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            sb.append(c);
            sb.append(c);
        }

        System.out.println(sb);
    }
}