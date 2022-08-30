package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    static int totalNumberOfTicketsPurchased = 0;
    static int totalIncome = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = s.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = s.nextInt();

        char[][] allSeats = new char[rows][numberOfSeats];
        for (int i = 0; i < allSeats.length; i++) {
            for (int j = 0; j < allSeats[i].length; j++) {
                allSeats[i][j] = 'S';
            }
        }

        boolean exit = false;
        while(!exit) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int input = s.nextInt();

            switch(input) {
                case 1:
                    showSeats(rows, numberOfSeats, allSeats);
                    break;
                case 2:
                    buyTicket(rows, numberOfSeats, allSeats, s);
                    break;
                case 3:
                    statistics(rows, numberOfSeats);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
    }

    public static void buyTicket(int rows, int numberOfSeats, char[][] allSeats, Scanner s) {
        int rowNumber, seatNumber;

        while (true) {
            System.out.println("Enter a row number:");
            rowNumber = s.nextInt();

            System.out.println("Enter a seat number in that row:");
            seatNumber = s.nextInt();

            // Validate input
            if (rowNumber <= 0 || rowNumber > rows) {
                System.out.println("Wrong input!");
                continue;
            }
            else if (seatNumber <= 0 || seatNumber > numberOfSeats) {
                System.out.println("Wrong input!");
                continue;
            }

            // Check if seat is already bought
            if (allSeats[rowNumber-1][seatNumber-1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            }
            else {
                allSeats[rowNumber - 1][seatNumber - 1] = 'B'; // Set seat to 'bought'
                break; // Break out of the while-loop
            }
        }

        // Calculate price of ticket
        int ticketPrice;
        if (rows * numberOfSeats < 60) {
            ticketPrice = 10;
        }
        else {
            int firstHalfOfRows = rows / 2;
            ticketPrice = rowNumber <= firstHalfOfRows ? 10 : 8;
        }

        System.out.printf("Ticket price: $%d\n", ticketPrice);

        totalNumberOfTicketsPurchased++;
        totalIncome += ticketPrice;

        System.out.println();
    }

    public static void showSeats(int rows, int numberOfSeats, char[][] allSeats) {
        // Print out the seats in the cinema
        System.out.println("Cinema:");
        for (int i = 0; i <= numberOfSeats; i++) {
            if (i == 0) System.out.print(" ");
            else System.out.print(i);
            if (i != numberOfSeats) System.out.print(" ");
        }

        System.out.println(); // Skip line; start printing out seats

        for (int i = 1; i <= rows; i++) {
            System.out.print(i + " ");
            for (char seat : allSeats[i - 1])
                System.out.print(seat + " ");
            System.out.println(); // Skip line
        }

        System.out.println();
    }

    public static void statistics(int rows, int numberOfSeats) {
        System.out.printf("Number of purchased tickets: %d\n", totalNumberOfTicketsPurchased);

        float percentageOfTicketsSold = (float) totalNumberOfTicketsPurchased / (float) (rows * numberOfSeats);

        System.out.printf("Percentage: %.2f%%\n", percentageOfTicketsSold * 100);
        System.out.printf("Current income: $%d\n", totalIncome);

        int priceOfRegularTicket = 10;
        int priceOfDiscountedTicket = 8;

        int totalPotentialIncome = 0;

        if (rows * numberOfSeats < 60) {
            totalPotentialIncome = rows * numberOfSeats * 10;
        }
        else {
            int firstHalfOfRows = rows / 2;
            int secondHalfOfRows = rows - firstHalfOfRows;
            totalPotentialIncome = firstHalfOfRows * numberOfSeats * priceOfRegularTicket + secondHalfOfRows * numberOfSeats * priceOfDiscountedTicket;
        }
        System.out.printf("Total income: $%d\n", totalPotentialIncome);

        System.out.println();
    }
}