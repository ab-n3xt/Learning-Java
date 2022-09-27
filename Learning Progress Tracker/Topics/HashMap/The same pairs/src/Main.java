import java.util.*;


class MapFunctions {

    public static void calcTheSamePairs(Map<String, String> map1, Map<String, String> map2) {
        // write your code here
        int numberOfPairs = 0;

        for (String k : map1.keySet()) {
            if (map2.containsKey(k)) {
                if (map1.get(k).equals(map2.get(k))) {
                    numberOfPairs++;
                }
            }
        }

        System.out.println(numberOfPairs);
    }

    public static void main(String[] args) {
        Map<Integer, String> m = new HashMap<>();

        m.put(27, "Fairy Godmother");
    }
}