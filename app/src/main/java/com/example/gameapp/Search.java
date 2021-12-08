package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameapp.adapters.GameInfoAdapter;
import com.example.gameapp.model.GameInfo;
import com.example.gameapp.model.Genre;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

public class Search extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView rvGameList;
    private Button btnSubmit;
    private EditText etDescription;
    private String description = "Halo 3";

    protected void onCreate(Bundle savedInstanceState) {
        // boilerplate for whenever there's networking
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //initialize and call external API
        IgdbClient gamesClient = new IgdbClient();

        //bottom navigation view setup
            etDescription = findViewById(R.id.etDescription);
            rvGameList = findViewById(R.id.rvGameList);
            btnSubmit = findViewById(R.id.btnSubmit);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description = etDescription.getText().toString();
            }
        });
            JSONArray gamesInfo = gamesClient.getSearch(description);

            List<GameInfo> games = new ArrayList<>();

            for (int i = 0; i < gamesInfo.length(); i++) {
                JSONObject game = (JSONObject) gamesInfo.get(i);
                ArrayList<Integer> genre = new ArrayList<>();
                try {
                    String title = game.getString("name");
                    String summary = game.getString("summary");
                    JSONArray genres = game.getJSONArray("genres");
                    for (Object num : genres) {
                        genre.add((Integer) num);
                    }
                    double rating = game.getDouble("rating");
                    int pictureID = game.getInt("id");
                    Log.i("covers", Integer.toString(pictureID));
                    games.add(new GameInfo(title, summary, rating, pictureID, genre));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            GameInfoAdapter gameInfoAdapter = new GameInfoAdapter(this, games);
            rvGameList.setAdapter(gameInfoAdapter);
            rvGameList.setLayoutManager(new LinearLayoutManager(this));
        }

}
