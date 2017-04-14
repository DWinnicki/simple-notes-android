package com.winnicki.simplenotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.winnicki.simplenotes.data.NoteList;
import com.winnicki.simplenotes.model.Note;

import java.io.Serializable;
import java.util.ArrayList;

public class SelectNoteTypeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewTextNote, imageViewPhotoNote, imageViewVideoNote, imageViewVoiceNote, buttonBack;

    NoteList noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_note_type);
        initialize();
    }

    public void initialize() {
        imageViewTextNote = (ImageView)findViewById(R.id.imageViewTextNote);
        imageViewPhotoNote = (ImageView)findViewById(R.id.imageViewPhotoNote);
        imageViewVideoNote = (ImageView)findViewById(R.id.imageViewVideoNote);
        imageViewVoiceNote = (ImageView)findViewById(R.id.imageViewVoiceNote);
        buttonBack = (ImageView) findViewById(R.id.buttonBack);

        imageViewTextNote.setOnClickListener(this);
        imageViewPhotoNote.setOnClickListener(this);
        imageViewVideoNote.setOnClickListener(this);
        imageViewVoiceNote.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

        Bundle extra = getIntent().getExtras();
        Serializable serializable = extra.getSerializable("noteList");
        noteList = (NoteList) serializable;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.imageViewTextNote:
                intent = new Intent(this, CreateTextNoteActivity.class);
                intent.putExtra("noteList", noteList);
                startActivity(intent);
                break;
            case R.id.imageViewPhotoNote:
                break;
            case R.id.imageViewVideoNote:
                break;
            case R.id.imageViewVoiceNote:
                break;
            case R.id.buttonBack:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("noteList", noteList);
                startActivity(intent);
                break;
        }
    }
}
