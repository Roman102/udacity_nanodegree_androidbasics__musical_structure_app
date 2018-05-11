package com.udacity.basicsnanodegree.android.musicalstructureapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link SongAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Song} objects.
 */
class SongAdapter extends ArrayAdapter<Song> {

    /**
     * Create a new {@link SongAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param songs   is the list of {@link Song}s to be displayed.
     */
    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID songname.
        TextView songNameTextView = (TextView) listItemView.findViewById(R.id.songname);
        // Get the song name from the currentSong object and set this text on
        // the song name - TextView.
        songNameTextView.setText(currentSong.getSongName());

        // Find the TextView in the list_item.xml layout with the ID artistname.
        TextView artistNameTextView = (TextView) listItemView.findViewById(R.id.artistname);
        // Get the artist's name from the currentSong object and set this text on
        // the artist's name - TextView.
        artistNameTextView.setText(currentSong.getArtistName());

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }

}
