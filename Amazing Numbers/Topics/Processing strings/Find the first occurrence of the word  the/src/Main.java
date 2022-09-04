import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String inputLine = scanner.nextLine();

        String[] words = inputLine.split(" ");

        int charIndex = 0;

        for (String s : words) {
            String word = s.toLowerCase();

            if (word.contains("the")) {
                System.out.println(charIndex);
                break;
            } else {
                charIndex += word.length() + 1; // +1 is for the space after the word
            }
        }

        if (charIndex >= inputLine.length()) {
            System.out.println(-1);
        }
    }
}