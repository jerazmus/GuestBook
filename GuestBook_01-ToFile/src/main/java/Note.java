public class Note {
    private String noteDate;
    private String name;
    private String note;

    Note(String noteDate, String name, String note) {
        this.noteDate = noteDate;
        this.name = name;
        this.note = note;
    }

    @Override
    public String toString() {
        return noteDate+"\n"+
                name+"\n"+
                note+"\n";
    }
}
