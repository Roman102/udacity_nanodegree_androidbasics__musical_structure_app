package com.udacity.basicsnanodegree.android.musicalstructureapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SongDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_song_details);

        setTitle(R.string.song_details);

        Intent intent = getIntent();
        Resources res = getResources();

        ((TextView) findViewById(R.id.songname)).setText(
                String.format(
                        res.getString(R.string.song_name),
                        intent.getStringExtra("CURRENT_SONG_NAME")
                )
        );

        ((TextView) findViewById(R.id.artistname)).setText(
                String.format(
                        res.getString(R.string.artist_name),
                        intent.getStringExtra("CURRENT_SONG_ARTIST")
                )
        );
    }

}
