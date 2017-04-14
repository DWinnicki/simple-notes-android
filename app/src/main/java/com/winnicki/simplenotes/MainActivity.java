package com.winnicki.simplenotes;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

import com.winnicki.simplenotes.data.Constants;
import com.winnicki.simplenotes.data.NoteList;
import com.winnicki.simplenotes.model.Note;
import com.winnicki.simplenotes.model.TextNote;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TabHost tabHost;
    Button buttonAddNote;
    ListView listViewAll;

    NoteList noteList = createNoteList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
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
                intent.putExtra("noteList", noteList);
                startActivity(intent);
            }
        });


        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            Serializable serializable = extra.getSerializable("noteList");
            if (serializable != null) {
                noteList = (NoteList) serializable;
            }
        }
        listViewAll = (ListView)findViewById(R.id.listViewAll);
        listViewAll.setOnItemClickListener(this);
        NotesAdapter adapter = new NotesAdapter(this, noteList.getNotes());
        listViewAll.setAdapter(adapter);
    }

    public NoteList createNoteList() {
        NoteList noteList = new NoteList();
        for(int i = 1; i < 6; i++) {
            Note note = new TextNote(i, "Text " + i, Constants.SHORT_TEXT);
            if((i % 3) == 0) {
                note.setPasswordProtected(true);
            }
            noteList.add(note);
        }
        return noteList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewNoteActivity.class);
        intent.putExtra("note", noteList.get(position));
        startActivity(intent);
    }
}
