package com.winnicki.simplenotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.winnicki.simplenotes.model.TextNote;

import java.io.Serializable;

public class ViewNoteActivity extends AppCompatActivity implements Switch.OnCheckedChangeListener {

    TextView title, content, date;
    Switch passwordProtection;
    TextNote textNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        initialize();
    }

    public void initialize() {
        title = (TextView)findViewById(R.id.title);
        content = (TextView)findViewById(R.id.content);
        date = (TextView)findViewById(R.id.date);
        passwordProtection = (Switch)findViewById(R.id.passwordProtected);

        Bundle bundle = getIntent().getExtras();
        Serializable serializable = bundle.getSerializable("note");
        textNote = (TextNote) serializable;

        if (textNote != null) {
            title.setText(textNote.getTitle());
            content.setText(textNote.getText());
            date.setText(textNote.getDate());
            if (textNote.isPasswordProtected()) {
                passwordProtection.setChecked(true);
            }
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        textNote.setPasswordProtected(isChecked);
    }
}
