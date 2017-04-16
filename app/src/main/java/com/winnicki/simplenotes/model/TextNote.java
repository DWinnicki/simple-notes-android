package com.winnicki.simplenotes.model;

import com.winnicki.simplenotes.data.EnumNoteType;

public class TextNote extends Note {
    private String content;

    public TextNote() {
        super(EnumNoteType.TEXT);
        this.content = "Undefined";
    }

    public TextNote(String title, String content) {
        super(title, EnumNoteType.TEXT);
        this.content = content;
    }

    public TextNote(int id, String title, String content) {
        super(id, title, EnumNoteType.TEXT);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nText: " + content + '\n';
    }
}
