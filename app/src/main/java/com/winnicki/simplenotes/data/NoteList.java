package com.winnicki.simplenotes.data;

import android.os.NetworkOnMainThreadException;

import com.winnicki.simplenotes.model.Note;
import com.winnicki.simplenotes.model.TextNote;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteList implements Serializable {
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

    public ArrayList<Note> getTextNotes() {
        return search(EnumNoteType.TEXT).getNotes();
    }

    public ArrayList<Note> getPhotoNotes() {
        return search(EnumNoteType.PHOTO).getNotes();
    }

    public ArrayList<Note> getVideoNotes() {
        return search(EnumNoteType.VIDEO).getNotes();
    }

    public ArrayList<Note> getVoiceNotes() {
        return search(EnumNoteType.VOICE).getNotes();
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public int size() {
        return notes.size();
    }

    public void add(Note note) {
        notes.add(note);
    }

    public Note get(int i) {
        return notes.get(i);
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
