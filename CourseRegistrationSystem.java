import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String courseCode;
    String title;
    String description;
    int capacity;
    String schedule;

    Course(String code, String title, String description, int capacity, String schedule) {
        this.courseCode = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    int studentID;
    String name;
    List<Course> registeredCourses;

    Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    void registerForCourse(Course course) {
        if (course.capacity > 0) {
            registeredCourses.add(course);
            course.capacity--;
            System.out.println(name + " registered for " + course.title);
        } else {
            System.out.println("Sorry, " + course.title + " is full.");
        }
    }

    void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.capacity++;
            System.out.println(name + " dropped " + course.title);
        } else {
            System.out.println(name + " is not registered for " + course.title);
        }
    }

    void displayRegisteredCourses() {
        System.out.println(name + "'s Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println(course.courseCode + ": " + course.title + " - " + course.schedule);
        }
    }
}

class CourseRegistrationSystem {
    List<Course> courseDatabase;
    List<Student> studentDatabase;

    CourseRegistrationSystem() {
        this.courseDatabase = new ArrayList<>();
        this.studentDatabase = new ArrayList<>();
    }

    void displayCourseListing() {
        System.out.println("Available Courses:");
        for (Course course : courseDatabase) {
            System.out.println(course.courseCode + ": " + course.title + " - " + course.schedule + " (" + course.capacity + " slots available)");
        }
    }

    void addCourse(Course course) {
        courseDatabase.add(course);
    }

    void addStudent(Student student) {
        studentDatabase.add(student);
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        // Adding sample courses
        system.addCourse(new Course("CSE101", "Introduction to Programming", "Learn programming basics", 30, "Mon-Wed-Fri"));
        system.addCourse(new Course("MTH201", "Calculus I", "Introductory calculus", 25, "Tue-Thu"));

        // Adding sample student
        system.addStudent(new Student(1, "John Doe"));

        while (true) {
            System.out.println("\n1. Display Available Courses");
            System.out.println("2. Display Registered Courses");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayCourseListing();
                    break;
                case 2:
                    // Display registered courses for a student
                    Student student = system.studentDatabase.get(0);
                    student.displayRegisteredCourses();
                    break;
                case 3:
                    // Register for a course
                    system.displayCourseListing();
                    System.out.print("Enter the course code to register: ");
                    String codeToRegister = scanner.next();
                    Course courseToRegister = system.courseDatabase.stream()
                            .filter(course -> course.courseCode.equals(codeToRegister))
                            .findFirst()
                            .orElse(null);
                    if (courseToRegister != null) {
                        student = system.studentDatabase.get(0);
                        student.registerForCourse(courseToRegister);
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;
                case 4:
                    // Drop a course
                    student = system.studentDatabase.get(0);
                    student.displayRegisteredCourses();
                    System.out.print("Enter the course code to drop: ");
                    String codeToDrop = scanner.next();
                    Course courseToDrop = student.registeredCourses.stream()
                            .filter(course -> course.courseCode.equals(codeToDrop))
                            .findFirst()
                            .orElse(null);
                    if (courseToDrop != null) {
                        student.dropCourse(courseToDrop);
                    } else {
                        System.out.println("Invalid course code.");
                    }
                    break;
                case 5:
                    // Exit the program
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
