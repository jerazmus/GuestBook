import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoteDAO {
    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Note> createGuestBook() {
        return new ArrayList<>();
    }

    public static void showMenu(ArrayList<Note> guestBook) {
        System.out.println("[1] Show guest book.");
        System.out.println("[2] Add guest note.");
        System.out.println("[3] Exit.");
        optionMenu(guestBook);
    }

    public static void optionMenu(ArrayList<Note> guestBook) {
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                showGuestBook(guestBook);
                break;
            case "2":
                addGuestNote(guestBook);
                break;
            case "3":
                System.exit(0);
            default:
                System.out.println("Wrong input.");
                showMenu(guestBook);
                break;
        }
    }

    public static void addGuestNote(ArrayList<Note> guestBook) {
        System.out.println("Your name:");
        String name = scanner.nextLine();
        System.out.println("Your note:");
        String message = scanner.nextLine();

        Note note = new Note(name, message);
        guestBook.add(note);

        try {
            CSVWriter csvWriter = new CSVWriter(new FileWriter("./GuestBook_02-ToCSV/GuestBook.csv"));
            List<String[]> rows = new ArrayList<>();

            String[] row  = {LocalDate.now().toString(), name, message};
            rows.add(row);

            csvWriter.writeAll(rows);
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMenu(guestBook);
    }

    public static void showGuestBook(ArrayList<Note> guestBook) {
        for(Note note : guestBook) {
            System.out.println(note);
        }
    }
}
