import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Person, Integer> map = new HashMap();
        map.put(new Person(scanner.nextLine()), 1995);
        map.put(new Person(scanner.nextLine()), 1995);

        System.out.println(map.size());
    }
}

class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Person p)) return false;

        return p.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }
}