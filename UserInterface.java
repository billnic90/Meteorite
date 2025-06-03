
//Bill Senyamwuzuza
/**
 * This class provides a user interface for the Meteorite program.
 * It allows the user to import meteorite data from a JSON file,
* display the data, save it to a binary file, and perform various options
 * on the data, such as finding meteorites by name or ID, displaying the largest   
 * meteorites, and listing meteorite classes.
 * 
 */
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import com.google.gson.Gson;

public class UserInterface {
    private Scanner scnr;
    private Meteorite[] meteorites;

    public UserInterface() {
        scnr = new Scanner(System.in);
        meteorites = new Meteorite[0];
    }

    /**
     * Launch the Meteorite program menu.
     */
    public void go() {

        int choice;// user choice
        int min_Choice = 0;// minimum number of options
        int max_Choice = 8;// maximum number of options

        do {

            displayMenu();// display user menu
            choice = inputInt(scnr, "Enter your choice:", min_Choice, max_Choice);// get user choice

            System.out.println();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    importData();
                    pause();
                    break;
                case 2:
                    displayData();
                    pause();
                    break;
                case 3:
                    SaveToBinaryFile();
                    pause();
                    break;
                case 4:
                    FindByName();
                    pause();
                    break;
                case 5:
                    FindByID();
                    pause();
                    break;
                case 6:
                    DisplayByMass();
                    pause();
                    break;
                case 7:
                    DisplayMostRecent();
                    pause();
                    break;
                case 8:
                    DisplayByClasses();
                    pause();
                    break;
                default:
                    System.out.println("That number is out of range.");
            }

        } while (choice != 0);
        scnr.close();
    }

    /**
     * Private helper method that displays the menu.
     */
    private void displayMenu() {
        System.out.println("Welcome to the NASA Meteorite tracking database.\n");
        System.out.println("\nHere's the menu of choices -");
        System.out.println("0) Quit");
        System.out.println("1) Import meteorite data from a JSON file");
        System.out.println("2) Display the metorite data");
        System.out.println("3) Export the meteorite data to a file");
        System.out.println("4) Find a meteorite by name");
        System.out.println("5) Find a meteorite by ID");
        System.out.println("6) List the largest meteorites");
        System.out.println("7) List the most recent meteorites by year");
        System.out.println("8) List the meteorite classes");

    }

    /**
     * (Mostly) Error-proof method to get an int in a specified range from the user.
     * 
     * @param input       - the Scanner object previously created for input.
     * @param prompt      - the message to be displayed for the input prompt.
     * @param min         - the smallest number allowed.
     * @param max         - the largest number allowed.
     * @param promptColor
     * @param inputColor
     * @param errorColor
     * @return the int entered by the user.
     */
    private int inputInt(Scanner input, String prompt, int min, int max) {
        int number = -1; // -1 Just to make the compiler happy.
        boolean wrong = true;
        do {
            try {
                System.out.print(prompt);
                number = Integer.parseInt(input.nextLine());

                if (number > max || number < min) {
                    System.out.printf("That number is out of range [%d to %d].  Try again.\n",
                            min, max);
                } else // good input
                {
                    wrong = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Make sure you enter an integer.  Try again.");
            }
        } while (wrong);

        return number;
    }

    public int inputInt(Scanner input) {
        return inputInt(input, "", Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Pause before loading menu again
     */
    private void pause() {
        System.out.println("\nPress Enter to continue..");
        scnr.nextLine();
    }

    /**
     * Method imports data from a JSON file
     */
    public void importData() {
        System.out.println(
                "Enter the JSON file name or press <>Enter> to accept the default (data/NASA_Meterorite.json):");
        String file = scnr.nextLine();
        if (file.isEmpty()) {
            file = "NASA_Meteorite.json";
        }
        Gson gson = new Gson();
        try (Reader reader = new FileReader(file)) {
            // import all data into a string
            meteorites = gson.fromJson(reader, Meteorite[].class);
            System.out.println(meteorites.length + " records processed");
        } catch (IOException e) {
            System.out.println("Error reading file:" + file);
        }
    }

    /**
     * Display meteorite data
     */
    public void displayData() {
        // check if data is available first
        if (meteorites.length == 0) {
            return;
        }
        // display data with toString method
        Arrays.stream(meteorites).forEach(w -> System.out.println(w));
    }

    /**
     * Save meteorite data to a binary file
     */
    public void SaveToBinaryFile() {
        // check if meteorite data is available
        if (meteorites.length == 0) {
            System.out.println("No data available to save");
            return;
        }
        String fileName = "Binary_Meteorite";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(meteorites);
            System.out.println("Data written successfully to " + fileName + " binary file");
        } catch (IOException e) {
            System.out.println("Error writting data to file: " + e.getMessage());
        }
    }

    /**
     * Find meteorite by name
     */
    public void FindByName() {
        if (meteorites.length == 0) {
            System.out.println("No meteorite data available");
            return;
        }
        System.out.println("Enter the name of the meteorite:");
        String name = scnr.nextLine();
        Optional<Meteorite> result = Arrays.stream(meteorites)
                .filter(w -> w.getName().toLowerCase().equals(name.toLowerCase())).findFirst();
        if (result.isPresent()) {
            System.out.println(result.get().display());
        } else {
            System.out.println("Meteorite does not exist in the file");
        }
    }

    /**
     * Find Meteorite by ID
     */
    public void FindByID() {
        if (meteorites.length == 0) {
            System.out.println("No meteorite data available");
            return;
        }
        System.out.print("Enter the ID of the meteorite: ");
        String id = scnr.nextLine();
        Optional<Meteorite> result = Arrays.stream(meteorites).filter(w -> w.getId().equals(id)).findFirst();
        if (result.isPresent()) {
            System.out.println(result.get().display());
        } else {
            System.out.println("Meteorite does not exist in the file");
        }
    }

    /**
     * Search and display largest meteorites by mass
     */
    public void DisplayByMass() {
        if (meteorites.length == 0) {
            System.out.println("No meteorite data available");
            return;
        }
        // validate user input
        int number = inputInt(scnr, "How many of the largest meteorites do you want to see?", 1, meteorites.length);
        List<Meteorite> result = Arrays.stream(meteorites)
                .sorted(Comparator.comparingDouble(w -> ((Meteorite) w).getMass()).reversed()).limit(number)
                .collect(Collectors.toList());

        System.out.println("Largest " + number + " meteorites:");
        for (Meteorite w : result) {
            System.out.println(" " + w.display());
        }
    }

    /**
     * List the most recent meteorites by year
     */
    public void DisplayMostRecent() {
        if (meteorites.length == 0) {
            System.out.println("No meteorite data available");
            return;
        }
        // validate user input
        int number = inputInt(scnr, "How many of the most recent meteorites do you want to see?", 1, meteorites.length);
        List<Meteorite> result = Arrays.stream(meteorites)
                .sorted(Comparator.comparingInt(w -> Integer.parseInt(((Meteorite) w).getYear())).reversed())
                .limit(number)
                .collect(Collectors.toList());
        System.out.println("Last " + number + " meteorites:");
        for (Meteorite w : result) {
            System.out.println(" " + w.display());
        }
    }

    /**
     * List the meteorite classes
     */
    public void DisplayByClasses() {
        if (meteorites.length == 0) {
            System.out.println("No meteorite data available");
            return;
        }
        Map<String, Long> count = Arrays.stream(meteorites).filter(w -> w.getRecclass() != null)
                .collect(Collectors.groupingBy(t -> t.getRecclass(), Collectors.counting()));
        // convert to list for sorting
        List<Map.Entry<String, Long>> sorted = new ArrayList<>(count.entrySet());
        sorted.sort(Map.Entry.<String, Long>comparingByValue().reversed());
        System.out.println("Meteorite classes:");
        System.out.println("Count  Classification");
        System.out.println("=====  ==============");
        for (Map.Entry<String, Long> w : sorted) {
            System.out.println(w.getValue() + "  " + w.getKey());
        }
    }

}
