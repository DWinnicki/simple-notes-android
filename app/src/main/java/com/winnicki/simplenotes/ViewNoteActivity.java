package com.winnicki.simplenotes;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.winnicki.simplenotes.database.SimpleNotesDbHelper;
import com.winnicki.simplenotes.model.TextNote;

import java.io.Serializable;

public class ViewNoteActivity extends AppCompatActivity implements Switch.OnCheckedChangeListener, View.OnClickListener, DialogInterface.OnClickListener {

    TextView title, content, date;
    Switch passwordProtection;
    TextNote textNote;
    ImageView imageViewBack, imageViewDelete;

    AlertDialog.Builder alert;
    AlertDialog dialogBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        initialize();
    }

    public void initialize() {
        title = (TextView)findViewById(R.id.title);
        content = (TextView)findViewById(R.id.content);
        date = (TextView)findViewById(R.id.createDate);
        passwordProtection = (Switch)findViewById(R.id.passwordProtected);
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewDelete = (ImageView)findViewById(R.id.imageViewDelete);
        imageViewBack.setOnClickListener(this);
        imageViewDelete.setOnClickListener(this);
        passwordProtection.setOnCheckedChangeListener(this);

        Bundle bundle = getIntent().getExtras();
        Serializable serializable = bundle.getSerializable("note");
        textNote = (TextNote) serializable;

        if (textNote != null) {
            title.setText(textNote.getTitle());
            content.setText(textNote.getContent());
            date.setText(textNote.getCreateDate());
            if (textNote.isPasswordProtected()) {
                passwordProtection.setChecked(true);
            }
        }

        alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete Note");
        alert.setMessage("Are you sure you want to delete?");
        alert.setNegativeButton("No", this);
        alert.setPositiveButton("Yes", this);
        dialogBox = alert.create();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        SimpleNotesDbHelper simpleNotesDbHelper = new SimpleNotesDbHelper(this);
        simpleNotesDbHelper.changePasswordProtection(textNote, isChecked);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewBack:
                finish();
                break;
            case R.id.imageViewDelete:
                dialogBox.show();
                break;
        }
    }

    private void processDelete() {
        SimpleNotesDbHelper simpleNotesDbHelper = new SimpleNotesDbHelper(this);
        simpleNotesDbHelper.deleteTextNote(textNote);
        finish();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                processDelete();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }
    }
}
