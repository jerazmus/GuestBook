import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Scanner;

public class NoteDAO {
    public static Scanner scanner = new Scanner(System.in);
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Note");

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

    public static ArrayList<Note> createGuestBook() {
        ArrayList<Note> guestBook = new ArrayList<>();

        return guestBook;
    }

    public static void addGuestNote(ArrayList<Note> guestBook) {
        System.out.println("Your name:");
        String name = scanner.nextLine();
        System.out.println("Your note:");
        String message = scanner.nextLine();
        Note note = new Note(name, message);

        guestBook.add(note);

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(note);
            et.commit();
        } catch (Exception e) {
            if(et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        showMenu(guestBook);
    }

    public static void showGuestBook(ArrayList<Note> guestBook) {
        guestBook.forEach(System.out::println);
        showMenu(guestBook);
    }
}