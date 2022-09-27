import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double radius = scanner.nextDouble();

        double s = Math.PI * Math.pow(radius, 2);

        System.out.println(s);
    }
}