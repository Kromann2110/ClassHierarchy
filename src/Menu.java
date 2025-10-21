import java.util.Scanner;

// This is an abstract class - you can't create Menu objects directly,
// but you can create subclasses that inherit from it
public abstract class Menu {

    // EXIT_OPTION is the number the user types to quit (default is 0)
    // Protected means subclasses can change this value if they want
    protected int EXIT_OPTION = 0;

    // The title/header that appears at the top of the menu
    private String header;

    // An array holding all the menu option texts (like "Add Person", "Delete Person", etc.)
    private String[] menuItems;

    // Scanner is used to read what the user types on the keyboard
    private Scanner scanner;

    // This is an abstract method - subclasses MUST implement this
    // It defines what happens when the user picks a menu option
    protected abstract void doAction(int option);

    // Constructor - this runs when you create a menu
    // You pass in the header text and then list all your menu options
    // Example: new MyMenu("Main Menu", "Add", "Edit", "Delete")
    public Menu(String header, String... menuItems) {
        this.header = header;
        this.menuItems = menuItems;
        this.scanner = new Scanner(System.in);
    }

    // This method runs the menu in a loop until the user chooses to exit
    public void run() {
        boolean done = false;

        // Keep showing the menu until done becomes true
        while (!done) {
            showMenu();              // 1. Show the menu options
            int option = getOption(); // 2. Get user's choice
            doAction(option);         // 3. Do whatever that option means

            // If user chose the exit option, stop the loop
            if (option == EXIT_OPTION) {
                done = true;
            }
        }
    }

    // Gets a valid menu option from the user
    // Keeps asking until they enter a valid number
    private int getOption() {
        int option = -1;           // Start with an invalid option
        boolean validInput = false; // Flag to track if input is valid

        // Keep looping until we get valid input
        while (!validInput) {
            System.out.print("Choose option: ");

            try {
                // Try to read an integer from the user
                option = scanner.nextInt();
                scanner.nextLine(); // This clears the "Enter" key press from the buffer

                // Check if the number is within valid range
                // Valid range is 0 (exit) to menuItems.length (last option)
                if (option >= 0 && option <= menuItems.length) {
                    validInput = true; // Input is good, exit the loop
                } else {
                    // Number is out of range
                    System.out.println("Invalid option! Please choose between 0 and " + menuItems.length);
                }
            } catch (Exception e) {
                // User typed something that's not a number (like "abc")
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear the bad input from the buffer
            }
        }

        return option; // Return the valid option
    }

    // Displays the menu with header and all options
    private void showMenu() {
        // Print a blank line, then the header
        System.out.println("\n" + header);

        // Print a line of equal signs under the header (same length as header)
        System.out.println("=".repeat(header.length()));

        // Loop through all menu items and print them with numbers
        // Note: we start numbering from 1 (not 0) for user friendliness
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }

        // Always print "0. Exit" as the last option
        System.out.println("0. Exit");
        System.out.println(); // Blank line for spacing
    }

    // Pauses the program and waits for user to press Enter
    // Useful after displaying information so user can read it before menu refreshes
    protected void pause() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine(); // Wait for Enter key
    }

    // Clears the screen by printing 50 blank lines
    // This pushes the old content up and out of view
    protected void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}