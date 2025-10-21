import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person {
    private String initials;
    private List<String> subjects;

    // Constructor — calls the superclass (Person) constructor
    public Teacher(int id, String name, String email, String initials) {
        super(id, name, email); // call Person constructor
        this.initials = initials;
        this.subjects = new ArrayList<>();
    }

    // Add a subject to the list
    public void addSubject(String subject) {
        subjects.add(subject);
    }

    // Override toString() to include initials and main subject
    @Override
    public String toString() {
        String mainSubject = subjects.isEmpty() ? "N/A" : subjects.get(0);
        // Combine Person info + Teacher-specific info
        return String.format("%-10d %-25s %-30s %-15s %-20s",
                getId(), getName(), getEmail(), initials, mainSubject);
    }
}
