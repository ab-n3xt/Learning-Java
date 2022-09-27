import java.util.*;

class MapUtils {

    public static SortedMap<String, Integer> wordCount(String[] strings) {
        // write your code here
        SortedMap<String, Integer> map = new TreeMap<String, Integer>();

        for (String s : strings) {
            if (map.putIfAbsent(s, 1) != null) {
                map.put(s, map.get(s)+1);
            }
        }

        return map;
    }

    public static void printMap(Map<String, Integer> map) {
        // write your code here
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }

}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split(" ");
        MapUtils.printMap(MapUtils.wordCount(words));
    }
}