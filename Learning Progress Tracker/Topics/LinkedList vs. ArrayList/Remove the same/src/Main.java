import java.util.*;

class ListOperations {
    public static void removeTheSame(LinkedList<String> linkedList, ArrayList<String> arrayList) {
        // write your code here
        ArrayList<Integer> commonStringIndexes = new ArrayList<>();
        for (int i = 0; i < linkedList.size(); i++) {
            String linkedListString = linkedList.get(i);
            String arrayListString = arrayList.get(i);

            if (linkedListString.equals(arrayListString)) {
                commonStringIndexes.add(i);
            }
        }

        for (int i = commonStringIndexes.size()-1; i >= 0; i--) {
            int index = commonStringIndexes.get(i);
            linkedList.remove(index);
            arrayList.remove(index);
        }
    }
}