import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Note {
    private LocalDate noteDate;
    private String name;
    private String note;

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
}
