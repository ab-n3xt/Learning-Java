public class Main {

    public static void main(String[] args) {
        int counter = 0;

        // write your code here
        for (Secret e : Secret.values()) {
            String s = e.toString();

            if (s.length() >= 4) {
                if (s.startsWith("STAR")) {
                    counter++;
                }
            }
        }

        System.out.println(counter);
    }
}
/*
enum Secret {
    STAR, CRASH, START, // ...
} */
