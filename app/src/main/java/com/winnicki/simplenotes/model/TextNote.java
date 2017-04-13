package com.winnicki.simplenotes.model;

import com.winnicki.simplenotes.data.EnumNoteType;

public class TextNote extends Note {
    private String text;

    public TextNote() {
        super(EnumNoteType.TEXT);
        this.text = "Undefined";
    }

    public TextNote(int id, String title, String text) {
        super(id, title, EnumNoteType.TEXT);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nText: " + text + '\n';
    }
}
