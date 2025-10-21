import java.util.Scanner;

// This extends Menu and handles person management
// I have to implement doAction() because Menu is abstract
public class ConsoleMenu extends Menu {

    // This is my data controller. All operations (Add, Remove, Find) use this manager object.
    private PersonManager manager;

    // Reads user input for names, grades, etc.
    private Scanner scanner;

    // Constructor - initializes the menu and links it to the PersonManager data object
    public ConsoleMenu(PersonManager manager) {
        // super() calls Menu constructor - MUST be first line
        // These strings become menu options 1-6
        super("Person Management System",
                "Add Person",
                "Remove Person",
                "Find Person",
                "Show All Persons",
                "Show All Teachers",
                "Show All Students");

        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    // Required method - decides what happens when user picks an option
    // This is the main switchboard for the entire program
    @Override
    protected void doAction(int option) {
        switch (option) {
            case 1:
                addPerson(); // Go to the add logic
                break;
            case 2:
                removePerson(); // Go to the remove logic
                break;
            case 3:
                findPerson(); // Go to the find logic
                break;
            case 4:
                showAllPersons();
                break;
            case 5:
                showAllTeachers();
                break;
            case 6:
                showAllStudents();
                break;
            case 0:
                // PERSISTENCE FIX: Save all data before the program closes.
                // This ensures all new students/teachers are saved to the .ser file.
                manager.saveData();
                System.out.println("Goodbye! Data saved.");
                break;
            default:
                System.out.println("Invalid option!");
        }
    }

    // Add a new Teacher or Student
    private void addPerson() {
        clear();
        System.out.println("=== ADD PERSON ===\n");

        // First ask if they want Teacher or Student
        System.out.println("What type of person?");
        System.out.println("1. Teacher");
        System.out.println("2. Student");

        // --- INPUT VALIDATION FOR TYPE (1 or 2) ---
        int type;
        // Loop forces the user to input a valid number (1 or 2) before continuing.
        while (true) {
            System.out.print("Choose: ");
            if (scanner.hasNextInt()) { // Check if input is an integer
                type = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()
                if (type == 1 || type == 2) {
                    break; // Valid input, exit loop
                }
            } else {
                scanner.nextLine(); // Consume the bad input (e.g., text)
            }
            System.out.println("Invalid choice. Please enter 1 for Teacher or 2 for Student.");
        }
        // --- END TYPE VALIDATION ---

        // --- INPUT VALIDATION FOR ID (Fixes InputMismatchException) ---
        int id;
        // Loop forces a valid integer input for the ID.
        while (true) {
            System.out.print("Enter ID (must be a number): ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine(); // Consume the newline/Enter key
                break; // Valid input, exit loop
            } else {
                System.out.println("Invalid input! ID must be a whole number.");
                scanner.nextLine(); // Consume the bad input (e.g., text)
            }
        }
        // --- END ID VALIDATION ---

        // Get basic info all persons need (Name and Email are safe using nextLine() after validation)
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        if (type == 1) {
            // Adding a Teacher
            System.out.print("Enter initials: ");
            String initials = scanner.nextLine();

            // Creates the Teacher object
            Teacher teacher = new Teacher(id, name, email, initials);

            // Add subjects
            // --- INPUT VALIDATION FOR numSubjects (Integer) ---
            int numSubjects;
            while (true) {
                System.out.print("How many subjects to add? ");
                if (scanner.hasNextInt()) {
                    numSubjects = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input! Must be a whole number.");
                    scanner.nextLine();
                }
            }
            // --- END numSubjects VALIDATION ---

            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter subject " + (i + 1) + ": ");
                String subject = scanner.nextLine();
                teacher.addSubject(subject);
            }

            // Try to add - manager checks for duplicate ID using Person.equals()
            if (manager.addPerson(teacher)) {
                System.out.println("\nTeacher added successfully!");
            } else {
                System.out.println("\nFailed to add teacher (ID already exists)");
            }

        } else if (type == 2) {
            // Adding a Student
            System.out.print("Enter education (e.g., CS, BA): ");
            String education = scanner.nextLine();

            // Creates the Student object
            Student student = new Student(id, name, email, education);

            // Add grades
            // --- INPUT VALIDATION FOR numGrades (Integer) ---
            int numGrades;
            while (true) {
                System.out.print("How many grades to add? ");
                if (scanner.hasNextInt()) {
                    numGrades = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input! Must be a whole number.");
                    scanner.nextLine();
                }
            }
            // --- END numGrades VALIDATION ---

            for (int i = 0; i < numGrades; i++) {
                System.out.print("Enter course name: ");
                String course = scanner.nextLine();

                // --- INPUT VALIDATION FOR Grade (double) ---
                double grade;
                while (true) {
                    System.out.print("Enter grade: ");
                    if (scanner.hasNextDouble()) { // Check for double since grades can be decimal
                        grade = scanner.nextDouble();
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Invalid input! Grade must be a number (e.g., 10.5 or 12).");
                        scanner.nextLine(); // Consume the bad input
                    }
                }
                // --- END Grade VALIDATION ---

                student.addGrade(course, grade);
            }

            if (manager.addPerson(student)) {
                System.out.println("\nStudent added successfully!");
            } else {
                System.out.println("\nFailed to add student (ID already exists)");
            }
        }

        pause(); // Wait for Enter before going back to menu
    }

    // Remove person by ID
    private void removePerson() {
        clear();
        System.out.println("=== REMOVE PERSON ===\n");

        // --- INPUT VALIDATION FOR ID (Remove) ---
        int id;
        while (true) {
            System.out.print("Enter ID of person to remove: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input! ID must be a whole number.");
                scanner.nextLine();
            }
        }
        // --- END ID VALIDATION ---

        // removePerson returns true if found and removed
        if (manager.removePerson(id)) {
            System.out.println("Person removed successfully!");
        } else {
            System.out.println("Person with ID " + id + " not found.");
        }

        pause();
    }

    // Find and display one person
    private void findPerson() {
        clear();
        System.out.println("=== FIND PERSON ===\n");

        // --- INPUT VALIDATION FOR ID (Find) ---
        int id;
        while (true) {
            System.out.print("Enter ID: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input! ID must be a whole number.");
                scanner.nextLine();
            }
        }
        // --- END ID VALIDATION ---

        // getPerson returns Person object or null if not found
        Person person = manager.getPerson(id);

        if (person != null) {
            System.out.println("\nFound:");

            // Print appropriate header based on person type
            // Note: This logic makes sure the header matches the object type (Teacher/Student)
            if (person instanceof Student) {
                System.out.printf("%-10s %-25s %-30s %-15s %-20s%n",
                        "ID", "NAME", "EMAIL", "EDUCATION", "AVG. GRADE");
            } else if (person instanceof Teacher) {
                System.out.printf("%-10s %-25s %-30s %-15s %-20s%n",
                        "ID", "NAME", "EMAIL", "INITIALS", "MAIN SUBJECT");
            } else {
                System.out.printf("%-10s %-25s %-30s%n",
                        "ID", "NAME", "EMAIL");
            }

            System.out.println("-------------------------------------------------------------------");
            // The magic: This calls the specific Student or Teacher toString() method (Polymorphism)
            System.out.println(person);

        } else {
            System.out.println("Person with ID " + id + " not found.");
        }

        pause();
    }

    // Show all persons (teachers and students together)
    private void showAllPersons() {
        clear();
        System.out.println("=== ALL PERSONS ===\n");

        // Print header for the combined list
        System.out.printf("%-10s %-25s %-30s %-15s %-20s%n",
                "ID", "NAME", "EMAIL", "EDUCATION/INIT", "AVG.GRADE/MAIN");
        System.out.println("-----------------------------------------------------------------------------------");

        // Loop through all persons and print each one (toString() handles the difference)
        for (Person p : manager.getAllPersons()) {
            System.out.println(p);
        }

        pause();
    }

    // Show only teachers
    private void showAllTeachers() {
        clear();
        System.out.println("=== ALL TEACHERS ===\n");

        // Print header specifically for Teachers
        System.out.printf("%-10s %-25s %-30s %-15s %-20s%n",
                "ID", "NAME", "EMAIL", "INITIALS", "MAIN SUBJECT");
        System.out.println("-----------------------------------------------------------------------------------");

        // Use manager's filtered list (getAllTeachers() uses 'instanceof' logic)
        for (Teacher t : manager.getAllTeachers()) {
            System.out.println(t);
        }

        pause();
    }

    // Show only students
    private void showAllStudents() {
        clear();
        System.out.println("=== ALL STUDENTS ===\n");

        // Print header specifically for Students
        System.out.printf("%-10s %-25s %-30s %-15s %-20s%n",
                "ID", "NAME", "EMAIL", "EDUCATION", "AVG. GRADE");
        System.out.println("-----------------------------------------------------------------------------------");

        // Use manager's filtered list (getAllStudents() uses 'instanceof' logic)
        for (Student s : manager.getAllStudents()) {
            System.out.println(s);
        }

        pause();
    }
}