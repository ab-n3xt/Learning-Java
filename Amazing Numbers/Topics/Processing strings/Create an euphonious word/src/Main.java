import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        String word = scanner.next();

        int minimumChanges = 0;
        int consonantCount = 0;
        int vowelCount = 0;

        for (char letter : word.toCharArray()) {
            if (isVowel(letter)) {
                vowelCount++;
                consonantCount = 0;
            }
            else {
                consonantCount++;
                vowelCount = 0;
            }

            if (vowelCount == 3) {
                vowelCount = 1;
                minimumChanges++;
            }
            else if (consonantCount == 3) {
                consonantCount = 1;
                minimumChanges++;
            }
        }

        System.out.println(minimumChanges);
    }

    public static boolean isVowel(char letter) {
        final char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        for (char c : vowels) {
            if (letter == c) {
                return true;
            }
        }

        return false;
    }
}