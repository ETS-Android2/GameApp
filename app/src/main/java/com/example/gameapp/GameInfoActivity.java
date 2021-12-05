package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.gameapp.model.GameInfo;
import com.example.gameapp.model.Genre;

import org.parceler.Parcels;

public class GameInfoActivity extends AppCompatActivity {


    TextView tvGameTitle;
    TextView tvGameSummary;
    TextView tvGameGenres;
    TextView tvGameRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        tvGameTitle = findViewById(R.id.tvGameTitle);
        tvGameSummary = findViewById(R.id.tvGameSummary);
        tvGameGenres = findViewById(R.id.tvGameGenres);
        tvGameRating = findViewById(R.id.tvGameRating);

        GameInfo gameInfo = Parcels.unwrap(getIntent().getParcelableExtra("gameInfo"));

        tvGameTitle.setText(gameInfo.getTitle());
        tvGameSummary.setText(gameInfo.getSummary());
        tvGameRating.setText(gameInfo.getRatingString());
        // genres?
        StringBuilder genres = new StringBuilder();
        for (int genre: gameInfo.getGenres()) {
            genres.append(new Genre().getName(genre));
            genres.append(", ");
        }
//        genres.delete(genres.length()-2, genres.length()); // strip final comma
        tvGameGenres.setText(genres.toString());
    }
}