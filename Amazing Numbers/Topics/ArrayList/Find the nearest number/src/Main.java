import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> numberList = new ArrayList<>();
        String inputString = scanner.nextLine();
        char[] inputChars = inputString.toCharArray();

        for (char c: inputChars) {
            if (c != ' ') {
                numberList.add(Integer.parseInt(String.valueOf(c)));
            }
        }

        int closeToNumber = scanner.nextInt();

        int distance = 0;

        boolean neighboursFound = false;

        do {
            int leftNumber = closeToNumber - distance;
            int rightNumber = closeToNumber + distance;

            if (numberList.contains(closeToNumber)) {
                int closeNumberOccurences = Collections.frequency(numberList, closeToNumber);

                for (int i = 0; i < closeNumberOccurences; i++) {
                    System.out.print(closeToNumber + " ");
                }

                neighboursFound = true;
            }
            else if (numberList.contains(leftNumber)) {
                int leftNumberOccurances = Collections.frequency(numberList, leftNumber);
                int rightNumberOccurances = Collections.frequency(numberList, rightNumber);

                for (int i = 0; i < leftNumberOccurances; i++) {
                    System.out.print(leftNumber + " ");
                }

                for (int i = 0; i < rightNumberOccurances; i++) {
                    System.out.print(rightNumber + " ");
                }

                neighboursFound = true;
            }
            else if (numberList.contains(rightNumber)) {
                int rightNumberOccurences = Collections.frequency(numberList, rightNumber);

                for (int i = 0; i < rightNumberOccurences; i++) {
                    System.out.print(rightNumber + " ");
                }

                neighboursFound = true;
            }
            else {
                distance++;
            }
        } while (!neighboursFound);
    }
}
