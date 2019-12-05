import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;

class NoteDAO {
    public static Scanner scanner = new Scanner(System.in);

    static void createGuestBook() {
        GuestBook guestBook = new GuestBook();
        File file = new File("./GuestBook_01-ToFile/GuestBook.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                LocalDate currentDate = LocalDate.parse(scanner.nextLine());
                String name = scanner.nextLine();
                String message = scanner.nextLine();
                Note note = new Note(currentDate, name, message);
                guestBook.add(note);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist in provided path.");
        }
        mainInterface(guestBook);
    }

    private static void mainInterface(GuestBook guestBook) {

        System.out.println("[1] Show guest book.");
        System.out.println("[2] Add guest note.");
        System.out.println("[3] Exit.");

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
                mainInterface(guestBook);
                break;
        }
    }

    private static void showGuestBook(GuestBook guestBook) {
        guestBook.show();
        mainInterface(guestBook);
    }

    private static void addGuestNote(GuestBook guestBook) {
        System.out.println("Your name:");
        String name = scanner.nextLine();
        System.out.println("Your note:");
        String message = scanner.nextLine();

        Note note = new Note(name, message);
        guestBook.add(note);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./GuestBook_01-ToFile/GuestBook.txt", true))) {
            bw.write(LocalDate.now().toString());
            bw.newLine();
            bw.write(name);
            bw.newLine();
            bw.write(message);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainInterface(guestBook);
    }
}