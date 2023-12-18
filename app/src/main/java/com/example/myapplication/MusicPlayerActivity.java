package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MusicPlayerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Music[] musicList;
    private int currentMusicIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        // Retrieve the music list from MainActivity
        musicList = MainActivity.musicList;

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

        // Retrieve the current music index from the intent
        Intent intent = getIntent();
        currentMusicIndex = intent.getIntExtra("currentMusicIndex", 0);

        startMusicPlayback();
    }

    private void initUI() {
        // Initialize UI elements here (e.g., set text for TextViews)
        TextView titleTextView = findViewById(R.id.textTitle);
        TextView singerTextView = findViewById(R.id.textSinger);
        ImageView albumCoverImageView = findViewById(R.id.imageAlbumCover);

        // Update UI with the current music information
        titleTextView.setText(musicList[currentMusicIndex].getTitle());
        singerTextView.setText(musicList[currentMusicIndex].getSinger());
        albumCoverImageView.setImageResource(musicList[currentMusicIndex].getAlbumCover());

        // Set up click listeners for next and previous buttons
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNextMusic();
            }
        });

        Button btnPrevious = findViewById(R.id.btnPrevious);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPreviousMusic();
            }
        });
    }

    private void startMusicPlayback() {
        // Initialize MediaPlayer and load the music file
        mediaPlayer = MediaPlayer.create(this, musicList[currentMusicIndex].getResourceId());
        mediaPlayer.start();

        // Set up a completion listener to automatically play the next music when the current one finishes
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                playNextMusic();
            }
        });

        // Initialize UI elements and start music playback
        initUI();
    }

    private void playNextMusic() {
        // Increment the current music index
        currentMusicIndex = (currentMusicIndex + 1) % musicList.length;

        // Stop the current music and release resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Start playback for the next music
        startMusicPlayback();
    }

    private void playPreviousMusic() {
        // Decrement the current music index
        currentMusicIndex = (currentMusicIndex - 1 + musicList.length) % musicList.length;

        // Stop the current music and release resources
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Start playback for the previous music
        startMusicPlayback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
