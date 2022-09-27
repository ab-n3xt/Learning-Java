import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Main {
    public static void main(String[] args) {
        // put your code here
        Set<String> orderedSet = new TreeSet<>();
        Scanner scanner = new Scanner(System.in);

        int numberOfInputs = Integer.parseInt(scanner.nextLine());

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            orderedSet.add(s);
        }

        for (String s: orderedSet) {
            System.out.println(s);
        }
    }
}