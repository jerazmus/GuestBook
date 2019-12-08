import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@XmlRootElement
@XmlAccessorType()
public class Note {

    @XmlTransient
    private LocalDate noteDate;
    private String name;
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
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
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