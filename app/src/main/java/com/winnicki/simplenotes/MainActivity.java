package com.winnicki.simplenotes;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.winnicki.simplenotes.data.Constants;
import com.winnicki.simplenotes.data.NoteList;
import com.winnicki.simplenotes.database.SimpleNotesDbHelper;
import com.winnicki.simplenotes.model.Note;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TabHost tabHost;
    Button buttonAddNote;
    ListView listViewAll, listViewTextNote, listViewPhotoNote, listViewVideoNote, listViewVoiceNote;
    TextView textViewTotalNotes;
    Dialog dialog;

    NoteList noteList;

    NotesAdapter adapter;

    SimpleNotesDbHelper simpleNotesDbHelper;

    int clickedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleNotesDbHelper = new SimpleNotesDbHelper(this);
        noteList = simpleNotesDbHelper.getAllTextNotes();

        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        noteList = simpleNotesDbHelper.getAllTextNotes();
        adapter = new NotesAdapter(this, noteList.getNotes());
        listViewAll.setAdapter(adapter);
        textViewTotalNotes.setText(noteList.size() + " Notes");
    }

    public void initialize() {
        // Initialize tabhost
        tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        // Initialize tabs
        TabHost.TabSpec spec = tabHost.newTabSpec("All");
        spec.setContent(R.id.listViewAll);
        spec.setIndicator("All");
        tabHost.addTab(spec);

        TextView tabTextView = (TextView)tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tabTextView.setTextColor(Color.parseColor("#FFFFFF"));

        spec = tabHost.newTabSpec("Text");
        spec.setContent(R.id.listViewText);
        spec.setIndicator("", ContextCompat.getDrawable(this, R.drawable.text_note));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Photo");
        spec.setContent(R.id.listViewPhoto);
        spec.setIndicator("", ContextCompat.getDrawable(this, R.drawable.photo_note));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Video");
        spec.setContent(R.id.listViewVideo);
        spec.setIndicator("", ContextCompat.getDrawable(this, R.drawable.video_note));
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Voice");
        spec.setContent(R.id.listViewVoice);
        spec.setIndicator("", ContextCompat.getDrawable(this, R.drawable.voice_note));
        tabHost.addTab(spec);

        buttonAddNote = (Button)findViewById(R.id.buttonAddNote);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectNoteTypeActivity.class);
                startActivity(intent);
            }
        });

        listViewAll = (ListView)findViewById(R.id.listViewAll);
        listViewAll.setOnItemClickListener(this);
        adapter = new NotesAdapter(this, noteList.getNotes());
        listViewAll.setAdapter(adapter);

        listViewTextNote = (ListView)findViewById(R.id.listViewText);
        listViewTextNote.setOnItemClickListener(this);
        adapter = new NotesAdapter(this, noteList.getTextNotes());
        listViewTextNote.setAdapter(adapter);

        listViewPhotoNote = (ListView)findViewById(R.id.listViewPhoto);
        listViewPhotoNote.setOnItemClickListener(this);
        adapter = new NotesAdapter(this, noteList.getPhotoNotes());
        listViewPhotoNote.setAdapter(adapter);

        listViewVideoNote = (ListView)findViewById(R.id.listViewVideo);
        listViewVideoNote.setOnItemClickListener(this);
        adapter = new NotesAdapter(this, noteList.getVideoNotes());
        listViewVideoNote.setAdapter(adapter);

        listViewVoiceNote = (ListView)findViewById(R.id.listViewVoice);
        listViewVoiceNote.setOnItemClickListener(this);
        adapter = new NotesAdapter(this, noteList.getVoiceNotes());
        listViewVoiceNote.setAdapter(adapter);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.password_dialog);
        dialog.setTitle("Password Protected.");

        final EditText editTextPassword = (EditText)dialog.findViewById(R.id.editTextPassword);
        Button buttonOK = (Button)dialog.findViewById(R.id.buttonOK);
        Button buttonCancel = (Button)dialog.findViewById(R.id.buttonCancel);
        final TextView textViewIncorrectPassword = (TextView)dialog.findViewById(R.id.textViewIncorrectPassword);

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextPassword.getText().toString().equals(Constants.password)) {
                    editTextPassword.setText(null);
                    textViewIncorrectPassword.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, ViewNoteActivity.class);
                    intent.putExtra("note", noteList.get(clickedNote));
                    startActivity(intent);
                } else {
                    textViewIncorrectPassword.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        textViewTotalNotes = (TextView)findViewById(R.id.textViewTotalNotes);
        textViewTotalNotes.setText(noteList.size() + " Notes");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        clickedNote = position;
        Note note = noteList.get(position);
        if(note.isPasswordProtected()) {
            dialog.show();
        } else {
            Intent intent = new Intent(this, ViewNoteActivity.class);
            intent.putExtra("note", noteList.get(position));
            startActivity(intent);
        }
    }
}
