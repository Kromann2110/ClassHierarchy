import java.io.*; // <-- NEW: Import all I/O classes for file operations
import java.util.ArrayList;
import java.util.List;

// 1. Implement Serializable to allow Java to save and load objects of this class.
public class PersonManager implements Serializable {

    private static final long serialVersionUID = 1L; // NEW: Recommended ID for version control
    private List<Person> persons;
    private static final String DATA_FILE = "school_data.ser"; // NEW: Define the file name

    // 2. Constructor - initializes the empty list and attempts to load saved data.
    public PersonManager() {
        this.persons = new ArrayList<>();
        loadData(); // <-- NEW: Call loadData() here!
    }

    // --- NEW METHOD: LOAD DATA ---
    public void loadData() {
        // Tries to load the saved list from the file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            // Read the entire List<Person> object from the file
            this.persons = (List<Person>) ois.readObject();
            System.out.println("Data loaded successfully from " + DATA_FILE);
        } catch (FileNotFoundException e) {
            // This happens the first time the program runs
            System.out.println("No existing data file found. Starting with an empty list.");
        } catch (IOException | ClassNotFoundException e) {
            // Handle errors during the reading process
            System.err.println("Error loading data: " + e.getMessage());
            // It's safer to clear the list if loading fails, just in case of corruption
            this.persons = new ArrayList<>();
        }
    }

    // --- NEW METHOD: SAVE DATA ---
    public void saveData() {
        // Tries to save the current list to the file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            // Write the entire list object to the file
            oos.writeObject(this.persons);
            System.out.println("Data saved successfully to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // 3. Add person (with duplicate check)
    public boolean addPerson(Person person) {
        // Check if person with same id already exists
        if (getPerson(person.getId()) != null) {
            System.out.println("Person with ID " + person.getId() + " already exists!");
            return false;
        }
        persons.add(person);
        return true;
    }

    // 4. Remove person by ID
    public boolean removePerson(int id) {
        Person person = getPerson(id);
        if (person != null) {
            persons.remove(person);
            return true;
        }
        return false;
    }

    // 5. Get a specific person by ID
    public Person getPerson(int id) {
        // Note: The loop below implicitly uses the 'equals' method you defined in Person.
        // It's a functional way to find the person.
        for (Person person : persons) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    // 6. Get all persons
    public List<Person> getAllPersons() {
        return new ArrayList<>(persons);
    }

    // 7. Get only students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Student) {
                students.add((Student) person);
            }
        }
        return students;
    }

    // 8. Get only teachers
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for (Person person : persons) {
            if (person instanceof Teacher) {
                teachers.add((Teacher) person);
            }
        }
        return teachers;
    }

    // 9. Get count of persons
    public int getPersonCount() {
        return persons.size();
    }
}