package com.winnicki.simplenotes.data;

import com.winnicki.simplenotes.model.Note;

import java.util.ArrayList;

public class NoteList {
    private ArrayList<Note> notes = new ArrayList<>();

    public NoteList() {
        this.notes = new ArrayList<>();
    }

    public NoteList(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void remove(Note note) {
        notes.remove(note);
    }

    public void remove(int id) {
        notes.remove(search(id));
    }

    public Note search(int id) {
        Note note = new Note();

        for(Note current : notes) {
            if(current.getId() == id) {
                note = current;
            }
        }

        return note;
    }

    public NoteList search(EnumNoteType type) {
        NoteList noteList = new NoteList();

        for(Note current : notes) {
            if(current.getType() == type) {
                noteList.add(current);
            }
        }

        return noteList;
    }

    @Override
    public String toString() {
        return "Total notes: " + notes.size() +
                '\n' + notes;
    }
}
