package com.winnicki.simplenotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.winnicki.simplenotes.model.Note;

import java.util.ArrayList;


class NotesAdapter extends ArrayAdapter<Note> {

    private ArrayList<Note> notes;

    NotesAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
        this.notes = notes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        Note note = notes.get(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_notes_row, parent, false);
        }

        // Lookup view for data population
        ImageView icon = (ImageView)convertView.findViewById(R.id.icon) ;
        TextView title = (TextView)convertView.findViewById(R.id.title);
        TextView date = (TextView)convertView.findViewById(R.id.createDate);
        ImageView passwordProtected = (ImageView)convertView.findViewById(R.id.passwordProtected);

        // Populate the data into the template view using the data object
        icon.setImageResource(note.getIcon());
        title.setText(note.getTitle());
        date.setText(note.getCreateDate());
        if(note.isPasswordProtected()) {
            passwordProtected.setImageResource(R.drawable.password);
        } else {
            passwordProtected.setImageResource(0);
        }

        // Return the completed view to render on screen
        return convertView;
    }
}
