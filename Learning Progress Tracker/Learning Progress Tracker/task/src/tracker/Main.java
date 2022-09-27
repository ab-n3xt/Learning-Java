package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    public enum CompareCategory {
        POPULARITY,
        ACTIVITY,
        DIFFICULTY;
    }
    public static class Student {
        String firstName, lastName, emailAddress;
        int[] points;

        public static ArrayList<String> emailAddressesTaken = new ArrayList<>();

        public Student(String firstName, String lastName, String emailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;
            this.points = new int[4];

            emailAddressesTaken.add(this.emailAddress);
        }

        public String getEmailAddress() {
            return this.emailAddress;
        }

        public void addPoints(int[] points) {
            for (int i = 0; i < this.points.length; i++) {
                this.points[i] += points[i];
            }
        }

        public String getPointsAsString() {
            return "points: " +
                    "Java=" + points[0] + "; " +
                    "DSA=" + points[1] + "; " +
                    "Databases=" + points[2] + "; " +
                    "Spring=" + points[3];
        }
    }

    public static class Course {
        private Set<Integer> studentsEnrolled;
        private int completedTasks, pointsAchieved, totalPointsAchievable;
        private String nameOfCourse;

        public Course(String nameOfCourse, int totalPointsAchievable) {
            this.studentsEnrolled = new TreeSet<>();
            this.completedTasks = 0;
            this.pointsAchieved = 0;

            this.totalPointsAchievable = totalPointsAchievable;
            this.nameOfCourse = nameOfCourse;
        }

        public double averagePointsPerTask() {
            return new BigDecimal(this.totalPointsAchievable / this.completedTasks)
                    .setScale(1, RoundingMode.HALF_UP)
                    .doubleValue();
        }

        public int totalStudentsEnrolled() {
            return studentsEnrolled.size();
        }

        public int getCompletedTasks() {
            return this.completedTasks;
        }

        public void enrollStudent(Integer id) {
            studentsEnrolled.add(id);
        }

        public int totalPointsAchieved() {
            return this.pointsAchieved;
        }

        public void incrementCompletedTasks() {
            this.completedTasks++;
        }

        public void addPointsAchieved(int points) {
            this.pointsAchieved += points;
        }

        public static Comparator<Course> courseComparator(CompareCategory categoryName) {
            switch (categoryName) {
                case POPULARITY:
                    new Comparator<Course>() {
                        @Override
                        public int compare(Course c1, Course c2) {
                            int thisTotal = c1.totalStudentsEnrolled();
                            int thatTotal = c2.totalStudentsEnrolled();
                            return Integer.compare(thisTotal, thatTotal);
                        }
                    };
                case ACTIVITY:
                    new Comparator<Course>() {
                        @Override
                        public int compare(Course c1, Course c2) {
                            int thisTotal = c1.getCompletedTasks();
                            int thatTotal = c2.getCompletedTasks();
                            return Integer.compare(thisTotal, thatTotal);
                        }
                    };
                case DIFFICULTY:
                    new Comparator<Course>() {
                        @Override
                        public int compare(Course c1, Course c2) {
                            double thisTotal = c1.averagePointsPerTask();
                            double thatTotal = c2.averagePointsPerTask();
                            return Double.compare(thisTotal, thatTotal);
                        }
                    };
            }

            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, Student> studentList = new HashMap<>();

        HashMap<Integer, Course> courseList = new HashMap<>();
        String[] courseNames = new String[]{
            "Java", "DSA", "Databases", "Spring"
        };
        int[] coursePoints = new int[]{600, 400, 480, 550};
        for (int i = 0; i < courseNames.length; i++)
            courseList.put(i, new Course(courseNames[i], coursePoints[i]));


        System.out.println("Learning Progress Tracker");

        // MAIN LOOP
        while (scanner.hasNextLine()) {
            String inputFromUser = scanner.nextLine();

            if (inputFromUser.isBlank()) {
                System.out.println("No input.");
            }
            else if (inputFromUser.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return.");

                addStudents(scanner, studentList);
            }
            else if (inputFromUser.equals("list")) {
                if (studentList.keySet().isEmpty()) {
                    System.out.println("No students found");
                }
                else {
                    System.out.println("Students:");
                    for (int k : studentList.keySet()) {
                        System.out.println(k);
                    }
                }
            }
            else if (inputFromUser.equals("add points")) {
                System.out.println("Enter an id and points or 'back' to return:");
                addPoints(scanner, studentList, courseList);
            }
            else if (inputFromUser.equals("find")) {
                System.out.println("Enter an id or 'back' to return:");
                findStudent(scanner, studentList);
            }
            else if (inputFromUser.equals("statistics")) {
                System.out.println("Type the name of a course to see details or 'back' to quit:");
            }
            else if (inputFromUser.equals("exit")) {
                System.out.println("Bye!");
                break;
            }
            else if (inputFromUser.equals("back")) {
                System.out.println("Enter 'exit' to exit the program");
            }
            else {
                System.out.println("Unknown command!");
            }
        }
    }

    private static void statistics(Scanner scanner,
                                   HashMap<Integer, Course> courseList) {
        System.out.println(allCourseStatistics(courseList));
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("back")) break;
            else {
                switch(input) {
                    case "java" -> courseStatistics(courseList.get(0));
                    case "dsa" -> courseStatistics(courseList.get(1));
                    case "databases" -> courseStatistics(courseList.get(2));
                    case "spring" -> courseStatistics(courseList.get(3));
                    default -> System.out.println("Unknown course.");
                }
            }
        }
    }

    private static String allCourseStatistics(HashMap<Integer, Course> courseList) {
        ArrayList<Course> sortedCourseList = new ArrayList<Course>(courseList.values());
        sortedCourseList.removeIf(course -> course.totalStudentsEnrolled() == 0);
        sortedCourseList.sort(Course.courseComparator(CompareCategory.POPULARITY));
        StringBuilder mostPopular = new StringBuilder();
        for (Course c : sortedCourseList) {
            if (c.totalStudentsEnrolled() > 0) {

            }
        }
    }

    private static void courseStatistics(Course course) {
    }

    private static void findStudent(Scanner scanner,
                                    HashMap<Integer, Student> studentList) {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("back")) {
                break;
            }
            else {
                int id = Integer.parseInt(input);

                Student s = studentList.get(id);

                if (s != null) {
                    System.out.println(id + " " + s.getPointsAsString());
                }
                else {
                    System.out.println("No student is found for id=" + id + ".");
                }
            }
        }
    }

    private static void addPoints(Scanner scanner,
                                  HashMap<Integer, Student> studentList,
                                  HashMap<Integer, Course> courseList) {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("back")) {
                break;
            }
            else {
                String[] pointsToBeAdded = input.split(" ");
                int id;
                try {
                    id = Integer.parseInt(pointsToBeAdded[0]);
                }
                catch (NumberFormatException e) {
                    System.out.println("No student is found for id=" + pointsToBeAdded[0] + ".");
                    continue;
                }
                if (studentList.get(id) != null) {
                    if (pointsToBeAdded.length == 5) {
                        boolean incorrectPointFormat = false;
                        int[] points = new int[pointsToBeAdded.length-1];

                        for (int i = 0; i < points.length; i++) {
                            try {
                                int point = Integer.parseInt(pointsToBeAdded[i+1]);
                                if (point > 0) {
                                    points[i] = point;
                                }
                                else {
                                    System.out.println("Incorrect points format.");
                                    incorrectPointFormat = true;
                                    break;
                                }
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Incorrect points format.");
                                incorrectPointFormat = true;
                                break;
                            }
                        }

                        if (!incorrectPointFormat) {
                            studentList.get(id).addPoints(points);
                            // Statistics for Courses
                            for (int i = 0; i < points.length; i++) {
                                int point = points[i];

                                if (point > 0) {
                                    Course course = courseList.get(i);

                                    course.enrollStudent(id);
                                    course.addPointsAchieved(points[i]);
                                    course.incrementCompletedTasks();
                                }
                            }
                            System.out.println("Points updated.");
                        }
                    }
                    else {
                        System.out.println("Incorrect points format.");
                    }
                }
                else {
                    System.out.println("No student is found for id=" + id + ".");
                }
            }
        }
    }

    private static void addStudents(Scanner scanner,
                                    HashMap<Integer, Student> studentList) {
        int numberOfStudentsAdded = 0;
        int id = studentList.keySet().isEmpty() ? 10000 : Collections.max(studentList.keySet());

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("back")) {
                System.out.printf("Total %d students have been added%n", numberOfStudentsAdded);
                break;
            }
            else {
                String[] splitCredentials = input.split(" ");
                int lastIndex = splitCredentials.length - 1;
                if (splitCredentials.length < 3) {
                    System.out.println("Incorrect credentials.");
                }
                else {
                    String firstName = splitCredentials[0];
                    String[] lastName = Arrays.copyOfRange(splitCredentials, 1, lastIndex);
                    String emailAddress = splitCredentials[lastIndex];
                    if (correctFirstName(firstName)) {
                        if (correctLastName(lastName)) {
                            if (correctEmailAddress(emailAddress)) {
                                if (!Student.emailAddressesTaken.contains(emailAddress)) {
                                    studentList.put(id, new Student(firstName, String.join(" ", lastName), emailAddress));
                                    id++;
                                    numberOfStudentsAdded++;
                                    System.out.println("The student has been added.");
                                }
                                else {
                                    System.out.println("This email is already taken.");
                                }
                            }
                            else {
                                System.out.println("Incorrect email.");
                            }
                        }
                        else {
                            System.out.println("Incorrect last name.");
                        }
                    }
                    else {
                        System.out.println("Incorrect first name.");
                    }
                }
            }
        }
    }

    public static boolean correctEmailAddress(String emailAddress) {
        String regex = "(\\w|\\.)+@\\w+\\.\\w+";
        return emailAddress.matches(regex);
    }

    private static boolean correctLastName(String[] lastName) {
        for (String s: lastName) {
            if (!correctFirstName(s)) {
                return false;
            }
        }

        return true;
    }

    public static boolean correctFirstName(String firstName) {
        String regex = "[A-Za-z]+('|-)?[A-Za-z]+(?:(-|')?[a-zA-Z]+)*";
        return firstName.matches(regex);
    }
}
