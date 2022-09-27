import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String html = scanner.nextLine();

        Deque<Integer> childrenIndexes = new ArrayDeque<>();

        for (int i = 0; i < html.length(); i++) {
            char c = html.charAt(i);

            if (c == '<') {
                int closingBracketIndex = html.indexOf('>', i);
                String tag = html.substring(i, closingBracketIndex);

                // Closing tag
                if (tag.contains("/")) {
                    // Print everything before its opening tag
                    int startOfContent = childrenIndexes.pop();
                    System.out.println(html.substring(startOfContent, i));
                }
                // Opening tag
                else {
                    childrenIndexes.push(closingBracketIndex+1);
                }

                i = closingBracketIndex;
            }
        }
    }
}