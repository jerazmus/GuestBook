import java.util.ArrayList;

class GuestBook {
    private ArrayList<Note> guestBook;

    GuestBook() {
        guestBook = new ArrayList<>();
    }

    void add(Note note) {
        guestBook.add(note);
    }

    void show() {
        for(Note note : guestBook) {
            System.out.println(note);
        }
    }
}