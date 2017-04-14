package com.winnicki.simplenotes;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.winnicki.simplenotes.model.Note;

import java.util.ArrayList;

/**
 * Created by winnicki on 2017-04-14.
 */

public class NotesAdapter extends ArrayAdapter<Note> {

    private ArrayList<Note> notes;

    public NotesAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
        this.notes = notes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Note note = notes.get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_notes_row, parent, false);
        }

        // Lookup view for data population
        ImageView icon = (ImageView)convertView.findViewById(R.id.icon) ;
        TextView title = (TextView)convertView.findViewById(R.id.title);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        ImageView passwordProtected = (ImageView)convertView.findViewById(R.id.passwordProtected);

        // Populate the data into the template view using the data object
        icon.setImageResource(note.getIcon());
        title.setText(note.getTitle());
        date.setText(note.getDate());
        if(note.isPasswordProtected()) {
            passwordProtected.setImageResource(R.drawable.password);
        } else {
            passwordProtected.setImageResource(0);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
