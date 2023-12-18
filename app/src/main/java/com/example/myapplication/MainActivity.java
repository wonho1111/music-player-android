package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    public static Music[] musicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        musicList = new Music[]{
                new Music("overdrive", "post malone", R.raw.overdrive),
                new Music("circles", "post malone", R.raw.circles),
                new Music("out of time", "the weeknd", R.raw.out_of_time),
                new Music("as it was", "harry styles", R.raw.as_it_was),
                // Add more songs as needed
        };

        String[] values = new String[musicList.length];
        for (int i = 0; i < musicList.length; i++) {
            values[i] = musicList[i].getTitle();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String selectedMusicTitle = musicList[position].getTitle();

        // Create an intent to start the MusicPlayerActivity
        Intent intent = new Intent(this, MusicPlayerActivity.class);

        // Pass the selected music title to the intent
        intent.putExtra("selectedMusicTitle", selectedMusicTitle);

        // Start the activity
        startActivity(intent);
    }
}

class Music {
    private String title;
    private String singer;
    private int resourceId; // ID of the music resource (assuming it's a resource ID)

    // Constructor, getters, setters...\
    public Music(String title, String singer, int resourceId) {
        this.title = title;
        this.singer = singer;
        this.resourceId = resourceId;
    }

    // Add getters for title, singer, and resourceId
    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    public int getResourceId() {
        return resourceId;
    }
}