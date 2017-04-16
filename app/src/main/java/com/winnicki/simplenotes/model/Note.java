package com.winnicki.simplenotes.model;

import com.winnicki.simplenotes.R;
import com.winnicki.simplenotes.data.Constants;
import com.winnicki.simplenotes.data.EnumNoteType;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Note implements Serializable{
    private int id;
    private String title;
    private EnumNoteType type;
    private boolean passwordProtected;
    private String createDate;
    private int icon;

    public Note() {
        this.id = -1;
        this.title = "Undefined";
        this.type = EnumNoteType.UNDEFINED;
        this.passwordProtected = false;
        this.createDate = Constants.sqlDateFormat.format(Calendar.getInstance().getTime());
        this.icon = 0;
    }

    public Note(EnumNoteType type) {
        this.id = -1;
        this.title = "Undefine";
        this.type = type;
        this.passwordProtected = false;
        this.createDate = Constants.sqlDateFormat.format(Calendar.getInstance().getTime());
        this.icon = getIcon();
    }

    public Note(String title, EnumNoteType type) {
        this.id = -1;
        this.title = title;
        this.type = type;
        this.createDate = Constants.sqlDateFormat.format(Calendar.getInstance().getTime());
        this.icon = getIcon();
    }

    public Note(int id, String title, EnumNoteType type) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.createDate = Constants.sqlDateFormat.format(Calendar.getInstance().getTime());
        this.icon = getIcon();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title.substring(0,1).toUpperCase() +
                title.substring(1).toLowerCase();
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

    public String getCreateDate() {
        Date date = null;
        try {
            date = Constants.sqlDateFormat.parse(createDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Constants.noteDateFormat.format(date);
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getIcon() {
        switch (type) {
            case TEXT:
                setIcon(R.drawable.text_note);
                break;
            case PHOTO:
                setIcon(R.drawable.photo_note);
                break;
            case VIDEO:
                setIcon(R.drawable.video_note);
                break;
            case VOICE:
                setIcon(R.drawable.voice_note);
                break;
        }

        return icon;
    }

    private void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "\nNote id: " + id +
                "\nTitle: " + title +
                "\nType: " + type +
                "\nPassword Protected? " + (passwordProtected ? "Yes" : "No") +
                "\nCreation Date: " + createDate +
                "\nIcon: " + icon;
    }
}
