package com.udacity.basicsnanodegree.android.musicalstructureapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SongDetailsActivity extends AppCompatActivity {

    private TextView playButtonView;
    private TextView pauseButtonView;

    private TextView durationTextView;

    private int totalDurationSeconds;
    private int[] totalDurationParts;

    private int currentDurationSeconds;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_song_details);

        playButtonView = findViewById(R.id.play_button_view);
        pauseButtonView = findViewById(R.id.pause_button_view);

        durationTextView = (TextView) findViewById(R.id.duration);

        timer = new Timer();

        if (savedInstanceState != null) {
            playButtonView.setVisibility(savedInstanceState.getInt("playButtonView.visibility"));
            pauseButtonView.setVisibility(savedInstanceState.getInt("pauseButtonView.visibility"));

            currentDurationSeconds = savedInstanceState.getInt("currentDurationSeconds");
        } else {
            currentDurationSeconds = 0;
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

        totalDurationSeconds = intent.getIntExtra("CURRENT_SONG_DURATION", 0);
        totalDurationParts = getDurationParts(totalDurationSeconds);

        setCurrentDurationText(new int[]{0, 0, 0});

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setCurrentDurationText(getDurationParts(currentDurationSeconds));
                    }
                });

                if (pauseButtonView.getVisibility() == View.VISIBLE) {
                    if (currentDurationSeconds < totalDurationSeconds) {
                        currentDurationSeconds++;
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                stopSongPlayback(null);
                            }
                        });
                    }
                }
            }
        }, 0, 1000);
    }

    public void stopSongPlayback(View view) {
        playOrPauseSong(view);

        currentDurationSeconds = 0;
        setCurrentDurationText(getDurationParts(currentDurationSeconds));
    }

    private void setCurrentDurationText(int[] currentDurationParts) {
        durationTextView.setText(
                String.format(
                        getResources().getString(R.string.duration),
                        currentDurationParts[0], currentDurationParts[1], currentDurationParts[2],
                        totalDurationParts[0], totalDurationParts[1], totalDurationParts[2]
                )
        );
    }

    private int[] getDurationParts(int duration) {
        int[] durationParts = new int[3];

        durationParts[0] = duration / 3600;
        duration -= durationParts[0] * 3600;

        durationParts[1] = duration / 60;
        durationParts[2] = duration - durationParts[1] * 60;

        return durationParts;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        timer.cancel();

        outState.putInt("playButtonView.visibility", playButtonView.getVisibility());
        outState.putInt("pauseButtonView.visibility", pauseButtonView.getVisibility());

        outState.putInt("currentDurationSeconds", currentDurationSeconds);
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
