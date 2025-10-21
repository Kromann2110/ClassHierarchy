import java.io.Serializable; // <-- NEW: Import Serializable

public abstract class Person implements Serializable { // <-- NEW: Implement Serializable

    private static final long serialVersionUID = 1L; // <-- NEW: Best practice for serialization

    private int id;
    private String name;
    private String email;

    public Person(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        // Correctly formats the output
        return String.format("%-10d %-25s %-30s", id, name, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}