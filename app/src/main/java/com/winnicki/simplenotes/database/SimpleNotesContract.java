package com.winnicki.simplenotes.database;

import android.provider.BaseColumns;

final class SimpleNotesContract {

    private SimpleNotesContract() {}

    static class NotesEntry implements BaseColumns {

        static final String TABLE_NOTE_TYPES = "note_types";
        static final String COLUMN_TYPE_NAME = "type_name";

        static final String TABLE_NOTES = "notes";
        static final String COLUMN_ID = "_id";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_CONTENT = "content";
        static final String COLUMN_TYPE = "type";
        static final String COLUMN_PASSWORD_PROTECTED = "password_protected";
        static final String COLUMN_CREATED_DATE = "created_date";

        static final String DROP_TABLE_NOTES = "DROP TABLE IF EXISTS " + TABLE_NOTES;
        static final String DROP_TABLE_NOTE_TYPES = "DROP TABLE IF EXISTS " + TABLE_NOTE_TYPES;

        static final String CREATE_TABLE_NOTE_TYPES =
                "CREATE TABLE " + TABLE_NOTE_TYPES
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY, "
                        + COLUMN_TYPE_NAME + " TEXT NOT NULL " + ")";

        static final String CREATE_TABLE_NOTES =
                "CREATE TABLE " + TABLE_NOTES
                        + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_TITLE + " TEXT NOT NULL, "
                        + COLUMN_CONTENT + " TEXT NOT NULL, "
                        + COLUMN_TYPE + " INTEGER NOT NULL, "
                        + COLUMN_PASSWORD_PROTECTED + " BOOLEAN DEFAULT 0 NOT NULL, "
                        + COLUMN_CREATED_DATE + " DATETIME NOT NULL" + ")";
    }
}
