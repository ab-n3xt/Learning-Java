package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public enum Response {
        OK,
        ERROR
    }

    public static void main(String[] args) {
        String[] database = new String[100];
        Arrays.fill(database, "");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String[] inputLine = scanner.nextLine().split(" ");

            if (inputLine.length == 1) {
                if (inputLine[0].equals("exit")) {
                    break;
                }
                else {
                    System.out.println(Response.ERROR);
                    continue;
                }
            }

            String command = inputLine[0];
            String index = inputLine[1];
            String[] values = inputLine.length > 2 ?
                    Arrays.copyOfRange(inputLine, 2, inputLine.length) : null;

            String response = switch (command) {
                case "get" -> get(database, index);
                case "set" -> set(database, index, values);
                case "delete" -> delete(database, index);
                default -> Response.ERROR.toString();
            };

            System.out.println(response);
        }
    }

    public static String get(String[] database, String index) {
        try {
            int integerIndex = Integer.parseInt(index) - 1;
            String value = database[integerIndex];
            if (value.isEmpty()) throw new RuntimeException();
            return value;
        }
        catch (RuntimeException e){
            return Response.ERROR.toString();
        }
    }

    public static String set(String[] database, String index, String[] values) {
        try {
            int integerIndex = Integer.parseInt(index) - 1;
            String value = String.join(" ", values);
            database[integerIndex] = value;
            return Response.OK.toString();
        }
        catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            return Response.ERROR.toString();
        }
    }

    public static String delete(String[] database, String index) {
        try {
            int integerIndex = Integer.parseInt(index) - 1;
            database[integerIndex] = "";
            return Response.OK.toString();
        }
        catch (NumberFormatException | IndexOutOfBoundsException e) {
            return Response.ERROR.toString();
        }
    }
}
