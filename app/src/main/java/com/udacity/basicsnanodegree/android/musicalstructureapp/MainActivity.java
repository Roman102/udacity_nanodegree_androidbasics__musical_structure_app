package com.udacity.basicsnanodegree.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Create a list of mock songs
        ArrayList<Song> songs = new ArrayList<Song>();

        for (int i = 1; i < 1001; i++) {
            songs.add(new Song("Song " + i, "Artist " + i));
        }

        // Create a {@link SongAdapter}, whose data source is a list of {@link Song}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_main.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link SongAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Song} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song currentSong = (Song) parent.getItemAtPosition(position);

                // Create a new intent to open the {@link SongDetailsActivity}
                Intent songDetailsIntent = new Intent(MainActivity.this, SongDetailsActivity.class);

                songDetailsIntent.putExtra("CURRENT_SONG_NAME", currentSong.getSongName());
                songDetailsIntent.putExtra("CURRENT_SONG_ARTIST", currentSong.getArtistName());

                // Start the new activity
                startActivity(songDetailsIntent);
            }

        });
    }
}
