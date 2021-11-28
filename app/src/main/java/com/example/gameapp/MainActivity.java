package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.api.igdb.exceptions.RequestException;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize and call external API
        IgdbClient games = new IgdbClient();

        //get the top rated games
        games.getGame();

        //pass in the game ids from the JSONArray from the getGame function
        games.getCovers(153700);
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
    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}