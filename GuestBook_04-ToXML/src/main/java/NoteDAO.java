import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteDAO {
    public static final String filePath = "./GuestBook_04-ToXML/GuestBook.xml";
    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Note> createGuestBook() {
        ArrayList<Note> guestBook = new ArrayList<>();
        File file = new File(filePath);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Note.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Note note = (Note) jaxbUnmarshaller.unmarshal(file);
            guestBook.add(note);
        } catch (JAXBException e) {
            System.out.println("");
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
        Note note = new Note(LocalDate.now(), name, message);

        guestBook.add(note);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Note.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(note, new File(filePath));
        } catch (JAXBException e) {
            System.out.println("Problem occurred while trying to append to file.");
        }
        showMenu(guestBook);
    }

    public static void showGuestBook(ArrayList<Note> guestBook) {
        guestBook.forEach(System.out::println);
        showMenu(guestBook);
    }
}
