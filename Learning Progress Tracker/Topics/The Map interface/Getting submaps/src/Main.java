import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

class Main {
    public static void main(String[] args) {
        // put your code here
        SortedMap<Integer, String> map = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);

        String[] indexes = scanner.nextLine().split(" ");
        int startingIndex = Integer.parseInt(indexes[0]);
        int endingIndex = Integer.parseInt(indexes[1]);

        // Get rid of newline character
        String numberOfLines = scanner.nextLine();

        while (scanner.hasNextLine()) {
            String[] entry = scanner.nextLine().split(" ");

            map.putIfAbsent(Integer.parseInt(entry[0]), entry[1]);
        }

        for (int i = startingIndex; i <= endingIndex; i++) {
            System.out.println(i + " " + map.get(i));
        }
    }
}