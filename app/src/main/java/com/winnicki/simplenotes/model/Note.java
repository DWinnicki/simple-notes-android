package com.winnicki.simplenotes.model;

import com.winnicki.simplenotes.data.EnumNoteType;

import java.util.Date;

/**
 * Created by winnicki on 2017-04-12.
 */

public class Note {
    private int id;
    private String title;
    private EnumNoteType type;
    private boolean passwordProtected;
    private Date date;

    public Note() {
        this.id = -1;
        this.title = "Undefine";
        this.type = EnumNoteType.UNDEFINED;
        this.passwordProtected = false;
        this.date = new Date();
    }

    public Note(EnumNoteType type) {
        this.id = -1;
        this.title = "Undefine";
        this.type = type;
        this.passwordProtected = false;
        this.date = new Date();
    }

    public Note(int id, String title, EnumNoteType type) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.passwordProtected = false;
        this.date = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title.substring(0,1).toUpperCase();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EnumNoteType getType() {
        return type;
    }

    public void setType(EnumNoteType type) {
        this.type = type;
    }

    public boolean isPasswordProtected() {
        return passwordProtected;
    }

    public void setPasswordProtected(boolean passwordProtected) {
        this.passwordProtected = passwordProtected;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\nNote id: " + id +
                "\nTitle: " + title +
                "\nType: " + type +
                "\nPassword Protected? " + (passwordProtected ? "Yes" : "No") +
                "\nCreation Date: " + date;
    }
}
