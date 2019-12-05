import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteDAO {
    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Note> createGuestBook() {
        ArrayList<Note> guestBook = new ArrayList<>();
        File file = new File("./GuestBook_03-ToJSON/GuestBook.json");
        Jsonb jsonb = JsonbBuilder.create();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String fromJSON = scanner.nextLine();
                Note note = jsonb.fromJson(fromJSON, Note.class);
                guestBook.add(note);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist in provided path.");
        }
        return guestBook;
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
        Jsonb jsonb = JsonbBuilder.create();
        String noteJSON = jsonb.toJson(note);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./GuestBook_03-ToJSON/GuestBook.json", true))) {
            bw.write(noteJSON);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMenu(guestBook);
    }

    public static void showGuestBook(ArrayList<Note> guestBook) {
        guestBook.forEach(System.out::println);
        showMenu(guestBook);
    }
}
