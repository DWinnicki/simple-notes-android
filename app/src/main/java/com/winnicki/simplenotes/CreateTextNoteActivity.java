package com.winnicki.simplenotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.winnicki.simplenotes.data.NoteList;
import com.winnicki.simplenotes.model.TextNote;

import java.io.Serializable;

public class CreateTextNoteActivity extends AppCompatActivity implements View.OnClickListener {

    NoteList noteList;

    EditText title, content;
    Switch passwordProtected;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_text_note);
        initialize();
    }

    public void initialize() {
        Bundle extra = getIntent().getExtras();
        Serializable serializable = extra.getSerializable("noteList");
        noteList = (NoteList) serializable;

        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        passwordProtected = (Switch)findViewById(R.id.passwordProtected);
        save = (Button)findViewById(R.id.buttonSave);

        save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                processSave();
                break;
        }
    }

    private void processSave() {
        TextNote note = new TextNote();
        note.setId(noteList.size() + 1);
        note.setTitle(title.getText().toString());
        note.setText(content.getText().toString());
        note.setPasswordProtected(passwordProtected.isChecked());
        noteList.add(note);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("noteList", noteList);
        startActivity(intent);
    }
}
