package com.winnicki.simplenotes.model;

import com.winnicki.simplenotes.data.EnumNoteType;

/**
 * Created by winnicki on 2017-04-14.
 */

public class PhotoNote extends Note {
    private int photo;

    public PhotoNote() {
        super(EnumNoteType.PHOTO);
        this.photo = 0;
    }

    public PhotoNote(int id, String title, int photo) {
        super(id, title, EnumNoteType.PHOTO);
        this.photo = photo;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nPhot: " + photo + '\n';
    }
}