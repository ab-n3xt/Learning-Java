import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        HashMap<Character, Character> encryptMap = new HashMap<>();
        HashMap<Character, Character> decryptMap = new HashMap<>();

        String alphabet = scanner.nextLine();
        String crypticLetters = scanner.nextLine();

        for (int i = 0; i < alphabet.length(); i++) {
            char alphaChar = alphabet.charAt(i);
            char crypticChar = crypticLetters.charAt(i);

            encryptMap.put(alphaChar, crypticChar);
            decryptMap.put(crypticChar, alphaChar);
        }

        char[] stringToEncrypt = scanner.nextLine().toCharArray();

        StringBuilder sb = new StringBuilder();

        for (char c : stringToEncrypt) {
            sb.append(encryptMap.get(c));
        }
        System.out.println(sb);
        sb = new StringBuilder();

        char[] stringToDecrypt = scanner.nextLine().toCharArray();

        for (char c : stringToDecrypt) {
            sb.append(decryptMap.get(c));
        }

        System.out.println(sb);
    }
}