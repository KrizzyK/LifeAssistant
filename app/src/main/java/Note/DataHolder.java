package Note;

import java.util.List;

public class DataHolder {

    private List<Note> notes;

    public DataHolder() {}

    public List<Note> getNotes() {
        return notes;
    }
    public void addNote(Note note) {
        notes.add(note);
    }
    public void removeNote(int index) {
        notes.remove(index);
    }

}
