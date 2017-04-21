package com.winnicki.simplenotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.winnicki.simplenotes.database.SimpleNotesDbHelper;
import com.winnicki.simplenotes.model.TextNote;

public class CreateTextNoteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, content;
    Switch passwordProtected;
    Button save;
    ImageView buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_text_note);
        initialize();
    }

    public void initialize() {
        title = (EditText)findViewById(R.id.title);
        content = (EditText)findViewById(R.id.content);
        passwordProtected = (Switch)findViewById(R.id.passwordProtected);
        save = (Button)findViewById(R.id.buttonSave);
        buttonBack = (ImageView)findViewById(R.id.buttonBack);

        save.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSave:
                processSave();
                break;
            case R.id.buttonBack:
                finish();
                break;
        }
    }

    private void processSave() {
        if(title.getText() != null && !title.getText().toString().equals("") && content.getText() != null && !content.getText().toString().equals("")) {
            String titleString = title.getText().toString();
            String contentString = content.getText().toString();
            Boolean password = passwordProtected.isChecked();

            TextNote note = new TextNote();
            note.setTitle(titleString);
            note.setContent(contentString);
            note.setPasswordProtected(password);

            SimpleNotesDbHelper simpleNotesDbHelper = new SimpleNotesDbHelper(this);
            if (simpleNotesDbHelper.insertTextNote(titleString, contentString, password)) {
                Toast.makeText(this, "TextNote " + title.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Enter title and content.", Toast.LENGTH_SHORT).show();
        }
    }
}
