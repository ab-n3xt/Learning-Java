package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    public static class Student {
        String firstName, lastName, emailAddress;
        int[] points;

        // Helper list for unique email addresses
        public static ArrayList<String> emailAddressesTaken = new ArrayList<>();

        public Student(String firstName, String lastName, String emailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.emailAddress = emailAddress;

            this.points = new int[4];

            emailAddressesTaken.add(this.emailAddress);
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

        private static class StudentStats {
            int studentID;
            int totalPoints;
            int totalTasks;
        }

        private static class StudentStatComparator implements Comparator<StudentStats> {
            @Override
            public int compare(StudentStats thisStat, StudentStats thatStat) {
                int result = Integer.compare(thisStat.totalPoints, thatStat.totalPoints);
                return result == 0 ?
                        -(Integer.compare(thisStat.studentID, thatStat.studentID)) : result;
            }
        }

        String name;
        int pointThreshold;

        int totalPointsAccumulated;
        int totalTasksAccumulated;

        HashMap<Integer, StudentStats> studentStatsHashMap;

        public Course(String name, int pointThreshold) {
            this.name = name;
            this.pointThreshold = pointThreshold;

            this.totalPointsAccumulated = 0;
            this.totalTasksAccumulated = 0;

            this.studentStatsHashMap = new HashMap<>();
        }

        public void addStudentStats(int studentID, int points) {
            if (studentStatsHashMap.containsKey(studentID)) {
                StudentStats s = studentStatsHashMap.get(studentID);

                s.totalPoints += points;
                s.totalTasks++;
            }
            else {
                StudentStats s = new StudentStats();

                s.studentID = studentID;
                s.totalPoints = points;
                s.totalTasks = 1;

                studentStatsHashMap.put(studentID, s);
            }

            totalPointsAccumulated += points;
            totalTasksAccumulated++;
        }

        public int getNumberOfEnrollments() {
            return this.studentStatsHashMap.size();
        }

        public double getAveragePointsPerTask() {
            return this.totalTasksAccumulated == 0 ? 0.0 : (double) this.totalPointsAccumulated / this.totalTasksAccumulated;
        }

        public void getTopLearners() {
            System.out.println(this.name);
            System.out.println("id    points    completed");

            ArrayList<StudentStats> studentStatsList = new ArrayList<>(this.studentStatsHashMap.values());

            studentStatsList.sort(new StudentStatComparator());

            for (StudentStats s : studentStatsList) {
                double percentageComplete = ((double) s.totalPoints / this.pointThreshold) * 100.0;
                double percentageFormatted =
                        new BigDecimal(percentageComplete)
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue();
                System.out.printf("%-5d %-9d %.1f%% %n", s.studentID, s.totalPoints, percentageFormatted);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<Integer, Student> studentList = new HashMap<>();
        HashMap<Integer, Course> courseList = new HashMap<>();

        courseList.put(0, new Course("Java", 600));
        courseList.put(1, new Course("DSA", 400));
        courseList.put(2, new Course("Databases", 480));
        courseList.put(3, new Course("Spring", 550));

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
                findStatistics(scanner, courseList);
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

    private static void findStatistics(Scanner scanner,
                                       HashMap<Integer, Course> courseList) {

        findAllCoursesStatistics(courseList);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("back")) {
                break;
            }
            else {
                boolean foundCourse = false;

                for (Course c : courseList.values()) {
                    if (input.equals(c.name.toLowerCase())) {
                        c.getTopLearners();
                        foundCourse = true;
                        break;
                    }
                }

                if (!foundCourse) System.out.println("Unknown course.");


            }
        }
    }

    private static void findAllCoursesStatistics(HashMap<Integer, Course> courseList) {
        ArrayList<Course> courseArrayList = new ArrayList<>(courseList.values());

        System.out.println(findMostAndLeastPopularCourse(courseArrayList));
        System.out.println(findHighestAndLowestActiveCourse(courseArrayList));
        System.out.println(findEasiestAndHardestCourse(courseArrayList));
    }

    private static String findEasiestAndHardestCourse(ArrayList<Course> courseList) {
        StringBuilder sb = new StringBuilder();

        courseList.sort((thisCourse, thatCourse) -> {
            double thisAverage = thisCourse.getAveragePointsPerTask();
            double thatAverage = thatCourse.getAveragePointsPerTask();

            return -Double.compare(thisAverage, thatAverage);
        });

        // Edge case: no courses have students yet
        if (courseList.get(0).getAveragePointsPerTask() == 0) {
            return "Easiest course: n/a\nHardest course: n/a";
        }
        else {
            sb.append("Easiest course: ");
            double highestAverage = courseList.get(0).getAveragePointsPerTask();

            sb.append(courseList.get(0).name);

            for(int i = 1; i < courseList.size(); i++) {
                Course c = courseList.get(i);

                // There are multiple courses that share the same average
                if (c.getAveragePointsPerTask() == highestAverage) {
                    sb.append(", ").append(c.name);
                }
            }

            sb.append("\n");
            sb.append("Hardest course: ");

            double lowestAverage = -1.0;

            for (int i = courseList.size()-1; i >= 0; i--) {
                Course c = courseList.get(i);
                double average = c.getAveragePointsPerTask();

                if (average != highestAverage) {
                    // Found first course
                    if (lowestAverage == -1.0) {
                        lowestAverage = average;

                        sb.append(c.name);
                    }
                    // Found multiple courses with same activity values
                    else if (average == lowestAverage) {
                        sb.append(", ").append(c.name);
                    }
                }
            }

            // Edge case: none of the courses are the hard
            // (all courses are equally easy)
            if (lowestAverage == -1.0) sb.append("n/a");
        }

        return sb.toString();
    }

    private static String findHighestAndLowestActiveCourse(ArrayList<Course> courseList) {
        StringBuilder sb = new StringBuilder();

        courseList.sort((thisCourse, thatCourse) -> {
            int thisTotalTasks = thisCourse.totalTasksAccumulated;
            int thatTotalTasks = thatCourse.totalTasksAccumulated;

            return -Integer.compare(thisTotalTasks, thatTotalTasks);
        });

        // Edge case: no courses have students yet
        if (courseList.get(0).getNumberOfEnrollments() == 0) {
            return "Highest activity: n/a\nLowest activity: n/a";
        }
        else {
            sb.append("Highest activity: ");
            int highestActivity = courseList.get(0).totalTasksAccumulated;

            sb.append(courseList.get(0).name);

            for(int i = 1; i < courseList.size(); i++) {
                Course c = courseList.get(i);

                if (c.totalTasksAccumulated == highestActivity) {
                    sb.append(", ").append(c.name);
                }
            }

            sb.append("\n");
            sb.append("Lowest activity: ");

            int lowestActivity = -1;
            for (int i = courseList.size()-1; i >= 0; i--) {
                Course c = courseList.get(i);
                int activityLevel = c.totalTasksAccumulated;

                if (activityLevel != highestActivity) {
                    // Found first course
                    if (lowestActivity == -1) {
                        lowestActivity = activityLevel;

                        sb.append(c.name);
                    }
                    // Found multiple courses with same activity values
                    else if (activityLevel == lowestActivity) {
                        sb.append(", ").append(c.name);
                    }
                }
            }

            // Edge case: none of the courses are the least active
            // (all courses are equally active)
            if (lowestActivity == -1) sb.append("n/a");
        }

        return sb.toString();
    }

    private static String findMostAndLeastPopularCourse(ArrayList<Course> courseList) {
        StringBuilder sb = new StringBuilder();

        courseList.sort((thisCourse, thatCourse) -> {
            int thisNumberOfEnrollments = thisCourse.getNumberOfEnrollments();
            int thatNumberOfEnrollments = thatCourse.getNumberOfEnrollments();

            return -Integer.compare(thisNumberOfEnrollments, thatNumberOfEnrollments);
        });

        // Edge case: no courses have students yet
        if (courseList.get(0).getNumberOfEnrollments() == 0) {
            return "Most popular: n/a\nLeast popular: n/a";
        }
        else {
            sb.append("Most popular: ");
            int maxEnrollments = courseList.get(0).getNumberOfEnrollments();

            sb.append(courseList.get(0).name);

            for(int i = 1; i < courseList.size(); i++) {
                Course c = courseList.get(i);

                if (c.getNumberOfEnrollments() == maxEnrollments) {
                    sb.append(", ").append(c.name);
                }
            }

            sb.append("\n");
            sb.append("Least popular: ");

            int minEnrollments = -1;
            for (int i = courseList.size()-1; i >= 0; i--) {
                Course c = courseList.get(i);
                int numberOfEnrollments = c.getNumberOfEnrollments();
                if (numberOfEnrollments != maxEnrollments) {
                    // Found first course
                    if (minEnrollments == -1) {
                        minEnrollments = numberOfEnrollments;

                        sb.append(c.name);
                    }
                    // Found multiple courses with same enrollment values
                    else if (numberOfEnrollments == minEnrollments) {
                        sb.append(", ").append(c.name);
                    }
                }
            }

            // Edge case: none of the courses are the least popular
            // (all courses are equally popular)
            if (minEnrollments == -1) sb.append("n/a");
        }

        return sb.toString();
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
                                if (point >= 0) {
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

                        // All clear, add the points to the student's record
                        if (!incorrectPointFormat) {
                            studentList.get(id).addPoints(points);

                            for (int i = 0; i < points.length; i++) {
                                int coursePoints = points[i];

                                // Makes sure non-zero values are being
                                // added to the student's record.
                                if (coursePoints > 0) {
                                    courseList.get(i).addStudentStats(id, coursePoints);
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
