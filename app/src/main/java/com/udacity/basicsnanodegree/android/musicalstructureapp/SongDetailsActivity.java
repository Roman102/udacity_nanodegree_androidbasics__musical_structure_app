package com.udacity.basicsnanodegree.android.musicalstructureapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SongDetailsActivity extends AppCompatActivity {

    private TextView playButtonView;
    private TextView pauseButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_song_details);

        playButtonView = findViewById(R.id.play_button_view);
        pauseButtonView = findViewById(R.id.pause_button_view);

        if (savedInstanceState != null) {
            playButtonView.setVisibility(savedInstanceState.getInt("playButtonView.visibility"));
            pauseButtonView.setVisibility(savedInstanceState.getInt("pauseButtonView.visibility"));
        }

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

        int durationSeconds = intent.getIntExtra("CURRENT_SONG_DURATION", 0);

        int durationHours = durationSeconds / 3600;

        durationSeconds -= durationHours * 3600;

        int durationMinutes = durationSeconds / 60;

        durationSeconds -= durationMinutes * 60;


        ((TextView) findViewById(R.id.duration)).setText(
                String.format(
                        res.getString(R.string.duration),
                        0, 0, 0,
                        durationHours, durationMinutes, durationSeconds
                )
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("playButtonView.visibility", playButtonView.getVisibility());
        outState.putInt("pauseButtonView.visibility", pauseButtonView.getVisibility());
    }

    public void closeSongDetails(View v) {
        finish();
    }

    public void playOrPauseSong(View v) {
        if (playButtonView.getVisibility() == View.VISIBLE) {
            playButtonView.setVisibility(View.INVISIBLE);
            pauseButtonView.setVisibility(View.VISIBLE);
        } else {
            playButtonView.setVisibility(View.VISIBLE);
            pauseButtonView.setVisibility(View.INVISIBLE);
        }
    }

}
