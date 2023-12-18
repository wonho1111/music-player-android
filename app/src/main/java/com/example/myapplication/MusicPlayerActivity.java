package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MusicPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private String selectedMusicTitle;
    private Music selectedMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Button b = (Button)findViewById(R.id.btnBack);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnPlayPause = findViewById(R.id.btnPlayPause);
        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        //btnPlayPause.setText("Play");
                    } else {
                        mediaPlayer.start();
                        //btnPlayPause.setText("Pause");
                    }
                }
            }
        });

        // Retrieve the selected music title from the intent
        Intent intent = getIntent();
        selectedMusicTitle = intent.getStringExtra("selectedMusicTitle");

        // Find the Music object based on the title
        selectedMusic = findMusicByTitle(selectedMusicTitle);

        if (selectedMusic != null) {
            // Initialize UI elements and start music playback
            initUI();
            startMusicPlayback();
        } else {
            // Handle case where Music object is not found
            // You may display an error message or take appropriate action
            finish(); // Finish the activity
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private Music findMusicByTitle(String title) {
        for (Music music : MainActivity.musicList) {
            if (music.getTitle().equals(title)) {
                return music;
            }
        }
        return null;
    }

    private void initUI() {
        // Initialize UI elements here (e.g., set text for TextViews)
        TextView titleTextView = findViewById(R.id.textTitle);
        TextView singerTextView = findViewById(R.id.textSinger);

        titleTextView.setText(selectedMusic.getTitle());
        singerTextView.setText(selectedMusic.getSinger());
    }

    private void startMusicPlayback() {
        // Initialize MediaPlayer and load the music file
        mediaPlayer = MediaPlayer.create(this, selectedMusic.getResourceId());
        mediaPlayer.start();
    }

    // Rest of your code...
}