import java.util.ArrayList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        ArrayList<String> listOfGuests = new ArrayList<String>();
        for(int i = 0; i < 4; i++) {
            listOfGuests.add(scanner.next());
        }
        for(int i = 3; i >= 0; i--) {
            System.out.println(listOfGuests.get(i));
        }
    }
}