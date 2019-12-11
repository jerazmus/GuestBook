import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "notes")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuestBook {
    @XmlElement(name = "note")
    private ArrayList<Note> guestBook;

    public GuestBook() {
        this.guestBook = new ArrayList<>();
    }

    void add(Note note) {
        guestBook.add(note);
    }

    void show() {
        guestBook.forEach(System.out::println);
    }

    public ArrayList<Note> getGuestBook() {
        return guestBook;
    }

    public void setGuestBook(ArrayList<Note> guestBook) {
        this.guestBook = guestBook;
    }
}
