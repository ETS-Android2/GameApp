package com.example.gameapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
        //Log.i("TWITCH", games.getGame().get(0));
//        JSONArray datas = null;
//        try {
//            datas = ParseData.getJsonFromAssets(getApplicationContext(), "app/src/main/java/com/example/gameapp/data/Games.JSON");
//            Log.i("data", datas.getJSONObject(0).getString("name"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

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
            try {
                String title = game.getString("name");
                String summary = game.getString("summary");
                float rating = game.getFloat("rating") / 10;
                JSONArray genres = game.getJSONArray("genres");

                GameInfo gameInfo = new GameInfo(title, summary);
                gameInfo.setRating(rating);
                gameInfo.setGenres((ArrayList<Integer>) genres.toList());

                games.add(gameInfo);

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