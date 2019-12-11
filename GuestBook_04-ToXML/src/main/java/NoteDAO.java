import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class NoteDAO {
    public static final String filePath = "./GuestBook_04-ToXML/GuestBook.xml";
    public static Scanner scanner = new Scanner(System.in);

    public static GuestBook createGuestBook() {
        File file = new File(filePath);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GuestBook.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (GuestBook) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println("Problem occurred while trying to read the file.");
        }
        return new GuestBook();
    }

    public static void showMenu(GuestBook guestBook) {
        System.out.println("[1] Show guest book.");
        System.out.println("[2] Add guest note.");
        System.out.println("[3] Exit.");
        optionMenu(guestBook);
    }

    public static void optionMenu(GuestBook guestBook) {
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

    public static void addGuestNote(GuestBook guestBook) {
        System.out.println("Your name:");
        String name = scanner.nextLine();
        System.out.println("Your note:");
        String message = scanner.nextLine();
        Note note = new Note(LocalDate.now(), name, message);

        guestBook.add(note);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(GuestBook.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(guestBook, new File(filePath));
        } catch (JAXBException e) {
            System.out.println("Problem occurred while trying to append to file.");
        }
        showMenu(guestBook);
    }

    public static void showGuestBook(GuestBook guestBook) {
        guestBook.show();
        showMenu(guestBook);
    }
}
