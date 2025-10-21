import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create the central data holder (PersonManager)
        PersonManager manager = new PersonManager();

        // 2. Initialize Data (Keep all your existing Teacher and Student setup code)

        // Add teachers
        Teacher teacher1 = new Teacher(100, "Hans Nielsen", "hni@easv.dk", "hni");
        manager.addPerson(teacher1);

        Teacher teacher2 = new Teacher(101, "Niels Hansen", "nha@easv.dk", "nha");
        manager.addPerson(teacher2);

        Teacher t1 = new Teacher(202, "Bent H. Pedersen", "bhp@easv.dk", "bhp");
        t1.addSubject("Programming");
        t1.addSubject("OOP");
        manager.addPerson(t1);

        Teacher t2 = new Teacher(203, "Lone Jensen", "loj@easv.dk", "loj");
        t2.addSubject("Database");
        t2.addSubject("Systems Development");
        manager.addPerson(t2);

        // Add students with grades
        Student student1 = new Student(102, "Ib Boesen", "ibo@easv.dk", "CS");
        student1.addGrade("Programming", 12.0);
        student1.addGrade("Database", 10.0);
        manager.addPerson(student1);

        Student student2 = new Student(103, "Patrick Kromann", "pkc@easv.dk", "CS");
        student2.addGrade("Programming", 12.0);
        student2.addGrade("Database", 10.0);
        student2.addGrade("Web Development", 7.0);
        manager.addPerson(student2);

        Student student3 = new Student(105, "Bo Ibsen", "bib@easv.dk", "CS");
        student3.addGrade("Programming", 10.0);
        student3.addGrade("Mathematics", 7.0);
        student3.addGrade("Database", 8.5);
        manager.addPerson(student3);

        // --- Console Menu System Launch ---

        System.out.println("Starting School Management Console System...");

        // 3. Create an instance of the ConsoleMenu, passing the manager to it.
        ConsoleMenu menu = new ConsoleMenu(manager);

        // 4. Launch the menu system using the inherited run() method.
        // This starts the loop that displays the menu options.
        menu.run();

        // This line runs only after the user selects 0 (Exit).
        System.out.println("Application terminated successfully.");
    }
}