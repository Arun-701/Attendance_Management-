import java.io.*;
import java.util.*;
class Main {

    private static final String FILE_NAME = "attendance_records.txt";
    public static void main(String[] args) {
        List<String[]> attendanceList = new ArrayList<>(); 
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\nAttendance Management System");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance");
            System.out.println("3. Update Attendance");
            System.out.println("4. Delete Attendance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    markAttendance(attendanceList, scanner);
                    saveToFile(attendanceList);
                    break;
                case 2:
                    viewAttendance(attendanceList);
                    break;
                case 3:
                    updateAttendance(attendanceList, scanner);
                    saveToFile(attendanceList);
                    break;
                case 4:
                    deleteAttendance(attendanceList, scanner);
                    saveToFile(attendanceList);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void markAttendance(List<String[]> attendanceList, Scanner scanner) {
        String studentId = getNonEmptyInput(scanner, "Enter Student ID: ");
        String name = getNonEmptyInput(scanner, "Enter Name: ");
        String date = getValidDate(scanner, "Enter Date (YYYY-MM-DD): ");
        String status = getValidAttendanceStatus(scanner, "Enter Attendance Status (Present/Absent): ");

        attendanceList.add(new String[] { studentId, name.toUpperCase(), date, status.toUpperCase()	 });
        System.out.println("Attendance marked successfully!");
    }

    private static void viewAttendance(List<String[]> attendanceList) {
        if (attendanceList.isEmpty()) {
            System.out.println("No attendance records available.");
        } else {
            System.out.println("\nAttendance Records:");
            for (String[] record : attendanceList) {
                System.out.println("Student ID: " + record[0] + ", Name: " + record[1] + ", Date: " + record[2] + ", Status: " + record[3]);
            }
        }
    }

    private static void updateAttendance(List<String[]> attendanceList, Scanner scanner) {
        String studentId = getNonEmptyInput(scanner, "Enter Student ID: ");
        String date = getValidDate(scanner, "Enter Date (YYYY-MM-DD): ");
        String status = getValidAttendanceStatus(scanner, "Enter New Attendance Status (Present/Absent): ");
        boolean updated = false;
        for (String[] record : attendanceList) {
            if (record[0].equals(studentId) && record[2].equals(date)) {
                record[3] = status.toUpperCase();
                updated = true;
                System.out.println("Attendance updated successfully!");
                break;
            }
        }
        if (!updated) {
            System.out.println("Attendance record not found.");
        }
    }

    private static void deleteAttendance(List<String[]> attendanceList, Scanner scanner) {
        String studentId = getNonEmptyInput(scanner, "Enter Student ID: ");
        String date = getValidDate(scanner, "Enter Date (YYYY-MM-DD): ");
        boolean found = false;
        for (int i = 0; i < attendanceList.size(); i++) {
            String[] record = attendanceList.get(i);
            if (record[0].equals(studentId) && record[2].equals(date)) {
                attendanceList.remove(i); 
                found = true;
                System.out.println("Attendance record deleted.");
                break;
            }
        }
        if (!found) {
            System.out.println("Attendance record not found.");
        }
    }

    private static void saveToFile(List<String[]> attendanceList) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String[] record : attendanceList) {
                writer.write(String.join(", ", record) + "\n");
            }
            System.out.println("Data saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    private static int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number: ");
            }
        }
    }

    private static String getNonEmptyInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty!");
            }
        }
    }

    private static String getValidDate(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("\\d{4}-\\d{2}-\\d{2}")) { 
                return input;
            } else {
                System.out.println("Invalid date format! Please enter a date in the format YYYY-MM-DD.");
            }
        }
    }

    private static String getValidAttendanceStatus(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Present") || input.equalsIgnoreCase("Absent")) {
                return input;
            } else {
                System.out.println("Invalid status! Please enter 'Present' or 'Absent'.");
            }
        }
    }
}
