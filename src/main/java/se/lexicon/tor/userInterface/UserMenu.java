package se.lexicon.tor.userInterface;

import se.lexicon.tor.course.Course;
import se.lexicon.tor.dao.CourseDaoList;
import se.lexicon.tor.dao.StudentDaoList;
import se.lexicon.tor.student.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserMenu
{
    private static Scanner scanner = new Scanner(System.in);
    private static CourseDaoList courseDao;
    private static StudentDaoList studentDao;

    public static void start()
    {
        System.out.println("Welcome.\n\n");
        courseDao = new CourseDaoList();
        studentDao = new StudentDaoList();
        while (true) {
            menu();
        }
    }

    private static void menu()
    {
        System.out.println("'Create', 'Register', 'Remove', 'find', 'edit', or 'exit'\n");
        String input = scanner.nextLine().trim().toLowerCase();
        switch (input) {
            case "create":
                create();
                break;
            case "register":
                register();
                break;
            case "remove":
                unregister();
                break;
            case "find":
                find();
                break;
            case "edit":
                edit();
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Invalid command.");
        }
    }

    private static void create()
    {
        System.out.print("Student or course?:");
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "student":
                    Student student = new Student();
                    setupStudent(student);
                    studentDao.saveStudent(student);
                    return;
                case "course":
                    Course course = new Course();
                    setupCourse(course);
                    courseDao.saveCourse(course);
                    return;
                default:
                    System.out.println("Write either 'student' or 'course'");
            }
        }
    }

    private static void setupStudent(Student student)
    {
        System.out.println("Write 'done' at any point to finish student setup\nOr write skip to skip the current input\n");
        System.out.print("Name: ");
        String name = creationCheck();
        if (name == null) {
            return;
        } else if (!name.equals("skip")) {
            student.setName(name);
        }
        System.out.print("Id: ");
        String id = creationCheck();
        if (id == null) {
            return;
        } else if (!id.equals("skip")) {
            try {
                student.setId(Integer.parseInt(id));
            } catch (Exception e) {
                System.out.println("Invalid number, skipping");
            }
        }
        System.out.print("Email: ");
        String email = creationCheck();
        if (email == null) {
            return;
        } else if (!email.equals("skip")) {
            student.setEmail(email);
        }
        System.out.print("Address: ");
        String address = creationCheck();
        if (address == null) {
            return;
        } else if (!address.equals("skip")) {
            student.setAddress(address);
        }
    }

    private static void setupCourse(Course course)
    {
        System.out.println("Write 'done' at any point to finish course setup\nOr write skip to skip the current input\n");
        System.out.print("Course name: ");
        String name = creationCheck();
        if (name == null) {
            return;
        } else if (!name.equals("skip")) {
            course.setCourseName(name);
        }
        System.out.print("Id: ");
        String id = creationCheck();
        if (id == null) {
            return;
        } else if (!id.equals("skip")) {
            try {
                course.setId(Integer.parseInt(id));
            } catch (Exception e) {
                System.out.println("Invalid number, skipping");
            }
        }
        System.out.print("Start date in the form of yyyy-mm-dd: ");
        String date = creationCheck();
        if (date == null) {
            return;
        } else if (!date.equals("skip")) {
            try {
                course.setStartDate(LocalDate.parse(date));
            } catch (Exception e) {
                System.out.println("Invalid date, skipping");
            }
        }
        System.out.print("Week duration: ");
        String duration = creationCheck();
        if (duration == null) {
            return;
        } else if (!duration.equals("skip")) {
            course.setWeekDuration(Integer.parseInt(duration));
        }
    }

    private static String creationCheck()
    {
        String input = scanner.nextLine();
        switch (input) {
            case "skip":
                return "skip";
            case "done":
                return null;
            default:
                return input;
        }
    }

    private static void register()
    {
        try {
            List<Student> students = studentDao.findAll();
            for (Student student : students) {
                System.out.println(student.getId() + "\t" + student.getName());
            }
            System.out.print("\nPlease write the id of a student to enroll: ");
            Student student = studentDao.findById(Integer.parseInt(scanner.nextLine()));
            for (Course course : courseDao.findAll()) {
                StringBuilder stringer = new StringBuilder(String.valueOf(course.getId())).append("\t").append(course.getCourseName()).append("\t").append(course.getStartDate());
                System.out.println(stringer.toString());
            }
            System.out.print("\nPlease write the id of a course for the student to enroll: ");
            Course course = courseDao.findByCourseId(Integer.parseInt(scanner.nextLine()));
            course.register(student);
            System.out.println("Enrolled.");
        } catch (Exception e) {
            System.out.println("Invalid number, aborting");
        }
    }

    private static void unregister()
    {
        try {
            for (Course course : courseDao.findAll()) {
                StringBuilder stringer = new StringBuilder(String.valueOf(course.getId())).append("\t").append(course.getCourseName()).append("\t").append(course.getStartDate());
                System.out.println(stringer.toString());
            }
            System.out.print("\nPlease write the id of a course to remove a student from: ");
            Course course = courseDao.findByCourseId(Integer.parseInt(scanner.nextLine()));
            List<Student> students = course.getStudents();
            for (Student student : students) {
                System.out.println(student.getId() + "\t" + student.getName());
            }
            System.out.print("\nPlease write the id of a student to remove: ");
            Student student = studentDao.findById(Integer.parseInt(scanner.nextLine()));
            course.unregister(student);
            System.out.println("Removed.");
        } catch (Exception e) {
            System.out.println("Invalid number, aborting");
        }
    }

    private static void find(){
        System.out.println("Students:");
        List<Student> students = studentDao.findAll();
        for (Student student : students) {
            StringBuilder stringer = new StringBuilder(String.valueOf(student.getId())).append("\t").append(student.getName()).append("\t").append(student.getEmail()).append("\t").append(student.getAddress());
            System.out.println(stringer.toString());
        }
        System.out.println("Courses:");
        for (Course course : courseDao.findAll()) {
            StringBuilder stringer = new StringBuilder(String.valueOf(course.getId())).append("\t").append(course.getCourseName()).append("\t").append(course.getStartDate()).append("\t").append(course.getWeekDuration());
            System.out.println(stringer.toString());
            List<Student> enrolledStudents = course.getStudents();
            for (Student student: enrolledStudents){
                System.out.println("\t" + student.getId() + "\t" + student.getName());
            }
        }
    }

    private static void edit(){
        try{
        find();
        System.out.print("Do you want to change a student or course?: ");
        String input = scanner.nextLine().trim().toLowerCase();
        switch (input) {
            case "student":
                System.out.print("\nPlease write the id of a student to edit: ");
                Student student = studentDao.findById(Integer.parseInt(scanner.nextLine()));
                setupStudent(student);
                break;
            case "course":
                System.out.print("\nPlease write the id of a course to edit: ");
                Course course = courseDao.findByCourseId(Integer.parseInt(scanner.nextLine()));
                setupCourse(course);
                break;
            default:
                System.out.println("Invalid input.");
        }
        } catch (Exception e){
            System.out.println("Invalid number, aborting.");
        }
    }
}
