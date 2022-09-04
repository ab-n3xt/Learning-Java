import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numberList = new ArrayList<>();

        String listOfNumbers = scanner.nextLine();

        scanner.close();

        scanner = new Scanner(listOfNumbers);

        while (scanner.hasNext()) {
            numberList.add(Integer.valueOf(scanner.next()));
        }

        int size = numberList.size() % 2 == 1 ?
                numberList.size() / 2 + 1 :
                numberList.size() / 2;

        for (int i = 0; i < size; i++) {
            numberList.remove(i);
        }

        for (int j = numberList.size()-1; j >= 0; j--) {
            System.out.print(numberList.get(j) + " ");
        }
    }
}