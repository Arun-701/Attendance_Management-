import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String studentId;
    String studentName;
    ArrayList<String> attendanceDates;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendanceDates = new ArrayList<>();
    }

    public void markAttendance(String date) {
        attendanceDates.add(date);
    }

    public void displayAttendance() {
        System.out.println("Attendance for " + studentName + " (ID: " + studentId + "):");
        for (String date : attendanceDates) {
            System.out.println(" - " + date);
        }
    }
}

class AttendanceManager {
    ArrayList<Student> students;

    public AttendanceManager() {
        students = new ArrayList<>();
    }

    public void addStudent(String id, String name) {
        students.add(new Student(id, name));
        System.out.println("Student added successfully.");
    }

    public void markAttendance(String id, String date) {
        for (Student s : students) {
            if (s.studentId.equals(id)) {
                s.markAttendance(date);
                System.out.println("Attendance marked.");
                return;
            }
        }
        System.out.println("Student ID not found.");
    }

    public void viewAttendance(String id) {
        for (Student s : students) {
            if (s.studentId.equals(id)) {
                s.displayAttendance();
                return;
            }
        }
        System.out.println("Student ID not found.");
    }
}

public class AttendanceManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AttendanceManager manager = new AttendanceManager();

        while (true) {
            System.out.println("\n--- Attendance Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. View Attendance");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter student name: ");
                    String name = sc.nextLine();
                    manager.addStudent(id, name);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String date = sc.nextLine();
                    manager.markAttendance(id, date);
                    break;

                case 3:
                    System.out.print("Enter student ID to view attendance: ");
                    id = sc.nextLine();
                    manager.viewAttendance(id);
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
