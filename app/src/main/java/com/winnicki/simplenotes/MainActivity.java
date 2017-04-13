package com.winnicki.simplenotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.winnicki.simplenotes.data.Constants;
import com.winnicki.simplenotes.data.NoteList;
import com.winnicki.simplenotes.model.TextNote;

public class MainActivity extends AppCompatActivity {

    TextView tvTextNote;
    NoteList noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void initialize() {
        tvTextNote = (TextView)findViewById(R.id.tvTextNote);

        createNoteList();

        tvTextNote.setText(noteList.toString());
    }

    public void createNoteList() {
        TextNote textNote = new TextNote(1, "Test 1", Constants.LONG_TEXT);
        TextNote textNote2 = new TextNote(2, "Test 2", Constants.SHORT_TEXT);

        noteList = new NoteList();
        noteList.add(textNote);
        noteList.add(textNote2);
    }
}
