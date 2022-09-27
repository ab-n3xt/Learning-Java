import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        HashMap<String, Integer> wordCounter = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        String[] words = scanner.nextLine().toLowerCase().split(" ");

        for (String word : words) {
            if (wordCounter.putIfAbsent(word, 1) != null) {
                wordCounter.put(word, wordCounter.get(word)+1);
            }
        }

        wordCounter.forEach((k, v) -> System.out.println(k + " " + v));
    }
}