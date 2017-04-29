package com.winnicki.simplenotes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.winnicki.simplenotes.R;
import com.winnicki.simplenotes.data.Constants;
import com.winnicki.simplenotes.data.EnumNoteType;
import com.winnicki.simplenotes.data.NoteList;
import com.winnicki.simplenotes.model.Note;
import com.winnicki.simplenotes.model.PhotoNote;
import com.winnicki.simplenotes.model.TextNote;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import static com.winnicki.simplenotes.database.SimpleNotesContract.NotesEntry;

public class SimpleNotesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simple_notes.db";
    private static final int DATABASE_VERSION = 1;

    public SimpleNotesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NotesEntry.CREATE_TABLE_NOTE_TYPES);
        db.execSQL(NotesEntry.CREATE_TABLE_NOTES);

        insertNoteTypes(db, 1, "text");
        insertNoteTypes(db, 2, "photo");
        insertNoteTypes(db, 3, "video");
        insertNoteTypes(db, 4, "voice");

        insertFakeNotes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(NotesEntry.DROP_TABLE_NOTES);
        db.execSQL(NotesEntry.DROP_TABLE_NOTE_TYPES);
        onCreate(db);
    }

    private void insertFakeNotes(SQLiteDatabase db) {
        for(int i = 1; i < 11; i++) {
            TextNote textNote = new TextNote("Text Note", Constants.SHORT_TEXT);
            if((i % 3) == 0) {
                textNote.setPasswordProtected(true);
            }
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, (-2 * i));
            insertFakeNote(db, textNote.getTitle(), textNote.getContent(), 1 , textNote.isPasswordProtected(), c.getTime());
        }

        for(int i = 1; i < 11; i++) {
            PhotoNote photoNote = new PhotoNote("Photo Note", R.drawable.photo_note);
            if((i % 3) == 0) {
                photoNote.setPasswordProtected(true);
            }
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, (-3 * i));
            insertFakeNote(db, photoNote.getTitle(), String.valueOf(photoNote.getPhoto()), 2, photoNote.isPasswordProtected(), c.getTime());
        }
    }

    private boolean insertFakeNote(SQLiteDatabase db, String title, String content, int type, boolean passwordProtected, Date date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesEntry.COLUMN_TITLE, title);
        contentValues.put(NotesEntry.COLUMN_CONTENT, content);
        contentValues.put(NotesEntry.COLUMN_TYPE, type);
        contentValues.put(NotesEntry.COLUMN_PASSWORD_PROTECTED, passwordProtected);
        contentValues.put(NotesEntry.COLUMN_CREATED_DATE, Constants.sqlDateFormat.format(date));
        db.insert(NotesEntry.TABLE_NOTES, null, contentValues);
        return true;
    }

    public boolean insertTextNote(String title, String content, boolean passwordProtected) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesEntry.COLUMN_TITLE, title);
        contentValues.put(NotesEntry.COLUMN_CONTENT, content);
        contentValues.put(NotesEntry.COLUMN_TYPE, 1);
        contentValues.put(NotesEntry.COLUMN_PASSWORD_PROTECTED, passwordProtected);
        contentValues.put(NotesEntry.COLUMN_CREATED_DATE, Constants.sqlDateFormat.format(Calendar.getInstance().getTime()));
        db.insert(NotesEntry.TABLE_NOTES, null, contentValues);
        return true;
    }

    private boolean insertNoteTypes(SQLiteDatabase db, int _id, String type_name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesEntry.COLUMN_ID, _id);
        contentValues.put(NotesEntry.COLUMN_TYPE_NAME, type_name);
        db.insert(NotesEntry.TABLE_NOTE_TYPES, null, contentValues);
        return true;
    }

    public boolean deleteTextNote(TextNote textNote) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(NotesEntry.TABLE_NOTES, NotesEntry.COLUMN_ID + "=" + textNote.getId(), null) > 0;
    }

    public boolean changePasswordProtection(Note note, Boolean passwordProtected) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesEntry.COLUMN_PASSWORD_PROTECTED, passwordProtected);
        db.update(NotesEntry.TABLE_NOTES, contentValues, NotesEntry.COLUMN_ID + "=" + note.getId(), null);
        return true;
    }

    public NoteList getAllNotes() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                NotesEntry._ID,
                NotesEntry.COLUMN_TITLE,
                NotesEntry.COLUMN_CONTENT,
                NotesEntry.COLUMN_TYPE,
                NotesEntry.COLUMN_PASSWORD_PROTECTED,
                NotesEntry.COLUMN_CREATED_DATE
        };

        String sortOrder = NotesEntry.COLUMN_CREATED_DATE + " DESC";

        Cursor cursor = db.query(
                NotesEntry.TABLE_NOTES,                   // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        NoteList noteList = new NoteList();
        while(cursor.moveToNext()) {
            if(cursor.getInt(cursor.getColumnIndex(NotesEntry.COLUMN_TYPE)) == 1) {
                TextNote textNote = new TextNote();
                textNote.setId((cursor.getInt(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_ID))));
                textNote.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_TITLE)));
                textNote.setContent(cursor.getString(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_CONTENT)));
                textNote.setType(EnumNoteType.TEXT);
                textNote.setPasswordProtected(cursor.getInt(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_PASSWORD_PROTECTED)) == 1);
                textNote.setCreateDate(cursor.getString(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_CREATED_DATE)));
                noteList.add(textNote);
            } else if(cursor.getInt(cursor.getColumnIndex(NotesEntry.COLUMN_TYPE)) == 2) {
                PhotoNote photoNote = new PhotoNote();
                photoNote.setId((cursor.getInt(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_ID))));
                photoNote.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_TITLE)));
                photoNote.setPhoto(Integer.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_CONTENT))));
                photoNote.setType(EnumNoteType.PHOTO);
                photoNote.setPasswordProtected(cursor.getInt(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_PASSWORD_PROTECTED)) == 1);
                photoNote.setCreateDate(cursor.getString(cursor.getColumnIndexOrThrow(NotesEntry.COLUMN_CREATED_DATE)));
                noteList.add(photoNote);
            }
        }
        cursor.close();

        return noteList;
    }
}