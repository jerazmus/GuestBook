import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;

class NoteDAO {

    static void createGuestBook() {
        GuestBook guestBook = new GuestBook();
        File file = new File("./GuestBook_01-ToFile/GuestBook.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String currentDate = scanner.nextLine();
                String name = scanner.nextLine();
                String message = scanner.nextLine();
                Note note = new Note(currentDate, name, message);
                guestBook.add(note);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Files doesn't exist in provided path.");
        }
        mainInterface(guestBook);
    }

    private static void mainInterface(GuestBook guestBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[1] Show guest book.");
        System.out.println("[2] Add guest note.");
        System.out.println("[3] Exit.");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showGuestBook(guestBook);
                mainInterface(guestBook);
            case 2:
                addGuestNote(guestBook);
                mainInterface(guestBook);

            case 3:
                System.exit(0);
            default:
                System.out.println("Wrong input.");
                mainInterface(guestBook);
        }
        scanner.close();
    }

    private static void showGuestBook(GuestBook guestBook) {
        guestBook.show();
        mainInterface(guestBook);
    }

    private static void addGuestNote(GuestBook guestBook) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String currentDate = formatter.format(LocalDate.now());

        System.out.println("Your name:");
        String name = scanner.nextLine();
        System.out.println("Your note:");
        String message = scanner.nextLine();
        scanner.close();

        Note note = new Note(currentDate, name, message);
        guestBook.add(note);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./GuestBook_01-ToFile/GuestBook.txt", true))) {
            bw.write(currentDate);
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