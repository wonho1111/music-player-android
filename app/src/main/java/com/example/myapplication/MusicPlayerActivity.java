package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MusicPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private String musicTitle;
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
                        btnPlayPause.setText("Play");
                    } else {
                        mediaPlayer.start();
                        btnPlayPause.setText("Pause");
                    }
                }
            }
        });

        // Retrieve data from the intent
        Intent intent = getIntent();
        musicTitle = intent.getStringExtra("musicTitle");
        // Add similar lines for album cover and singer information

        startMusicPlayback();
        // Now, you can use the data in your UI or perform other actions
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startMusicPlayback() {
        // Initialize MediaPlayer and load the music file
        mediaPlayer = MediaPlayer.create(this, R.raw.overdrive); // Replace with your music file
        mediaPlayer.start();
    }
}
