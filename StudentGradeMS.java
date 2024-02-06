import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentGradeMS {
     private static Map<String, Student> studentRecords = new HashMap<>();
    private static final int TOTAL_SUBJECTS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n1. Add Student\n2. Update Student\n3. Delete Student\n4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    viewAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.next();

        int[] marks = new int[TOTAL_SUBJECTS];
        System.out.println("Enter marks for each subject:");
        for (int i = 0; i < TOTAL_SUBJECTS; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        double percentage = calculatePercentage(marks);
        char grade = calculateGrade(percentage);

        Student student = new Student(name, rollNumber, marks, percentage, grade);
        studentRecords.put(rollNumber, student);

        System.out.println("Student added successfully.");
    }

    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter roll number of the student to update: ");
        String rollNumber = scanner.next();

        if (studentRecords.containsKey(rollNumber)) {
            Student student = studentRecords.get(rollNumber);

            System.out.println("Current details for student with roll number " + rollNumber + ":");
            System.out.println(student);

            int[] marks = new int[TOTAL_SUBJECTS];
            System.out.println("Enter updated marks for each subject:");
            for (int i = 0; i < TOTAL_SUBJECTS; i++) {
                System.out.print("Subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }

            double percentage = calculatePercentage(marks);
            char grade = calculateGrade(percentage);

            student.setMarks(marks);
            student.setPercentage(percentage);
            student.setGrade(grade);

            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter roll number of the student to delete: ");
        String rollNumber = scanner.next();

        if (studentRecords.containsKey(rollNumber)) {
            studentRecords.remove(rollNumber);
            System.out.println("Student with roll number " + rollNumber + " deleted successfully.");
        } else {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    private static void viewAllStudents() {
        if (studentRecords.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            System.out.println("All Student Records:");
            for (Student student : studentRecords.values()) {
                System.out.println(student);
            }
        }
    }

    private static double calculatePercentage(int[] marks) {
        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        return (double) totalMarks / TOTAL_SUBJECTS;
    }

    private static char calculateGrade(double percentage) {
        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}

class Student {
    private String name;
    private String rollNumber;
    private int[] marks;
    private double percentage;
    private char grade;

    public Student(String name, String rollNumber, int[] marks, double percentage, char grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        this.percentage = percentage;
        this.grade = grade;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nRoll Number: " + rollNumber +
                "\nMarks: " + arrayToString(marks) +
                "\nPercentage: " + percentage +
                "\nGrade: " + grade + "\n";
    }

    private String arrayToString(int[] array) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            if (i < array.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
