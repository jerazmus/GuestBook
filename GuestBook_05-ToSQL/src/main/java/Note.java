import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noteID", unique = true)
    private int id;
    @Column(name = "date", nullable = false)
    private LocalDate noteDate;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "note", nullable = false)
    private String note;

    public Note() {}

    public Note(LocalDate noteDate, String name, String note) {
        this.noteDate = noteDate;
        this.name = name;
        this.note = note;
    }

    public Note(String name, String note) {
        this.noteDate = LocalDate.now();
        this.name = name;
        this.note = note;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(noteDate)+"\n"+
                name+"\n"+
                note+"\n";
    }

    public LocalDate getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(LocalDate noteDate) {
        this.noteDate = noteDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}