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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] values = { "song1", "song2", "song3", "song4", "song5",
                "song6", "song7"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
//        String item = (String) getListAdapter().getItem(position);
//        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
        String item = (String) getListAdapter().getItem(position);

        // Create an intent to start the MusicPlayerActivity
        Intent intent = new Intent(this, MusicPlayerActivity.class);

        // Pass data to the intent
        intent.putExtra("musicTitle", item);
        // Add similar lines for album cover and singer information

        // Start the activity
        startActivity(intent);
    }

}