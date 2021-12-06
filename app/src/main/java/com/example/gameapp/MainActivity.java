package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameapp.adapters.GameInfoAdapter;
import com.example.gameapp.model.GameInfo;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;
    private RecyclerView rvGameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // boilerplate for whenever there's networking
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize and call external API
        IgdbClient gamesClient = new IgdbClient();

        //get the top rated games
        JSONArray gamesInfo = gamesClient.getGamesInfo();

        //pass in the game ids from the JSONArray from the getGame function
        String coverUrl = gamesClient.getCovers(153700);
        gamesClient.getSearch("Halo 3");
        JSONArray gamesInfo2 = gamesClient.getGenreGame(2);


        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Toast.makeText(MainActivity.this, "Error logging out", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(MainActivity.this, "Logged out!", Toast.LENGTH_SHORT).show();
                        goLoginActivity();
                    }
                });

            }
        });

        rvGameList = findViewById(R.id.rvGameList);

        List<GameInfo> games = new ArrayList<>();

        for (int i = 0; i < gamesInfo.length(); i++) {
            JSONObject game = (JSONObject)gamesInfo.get(i);
            ArrayList<Integer> genre = new ArrayList<>();
            try {
                String title = game.getString("name");
                String summary = game.getString("summary");
                JSONArray genres = game.getJSONArray("genres");
                for(Object num :genres) {
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

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}