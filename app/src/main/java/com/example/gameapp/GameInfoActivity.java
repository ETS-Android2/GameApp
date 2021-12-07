package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.style.IconMarginSpan;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gameapp.model.GameInfo;
import com.example.gameapp.model.Genre;

import org.parceler.Parcels;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class GameInfoActivity extends AppCompatActivity {


    TextView tvGameTitle;
    TextView tvGameSummary;
    TextView tvGameGenres;
    TextView tvGameRating;
    ImageView ivImage;
    TextView tvWebsite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_info);

        tvGameTitle = findViewById(R.id.tvGameTitle);
        tvGameSummary = findViewById(R.id.tvGameSummary);
        tvGameGenres = findViewById(R.id.tvGameGenres);
        tvGameRating = findViewById(R.id.tvGameRating);
        ivImage = findViewById(R.id.ivImg);
        tvWebsite = findViewById(R.id.tvWebsites);

        GameInfo gameInfo = Parcels.unwrap(getIntent().getParcelableExtra("gameInfo"));
        IgdbClient gamesClient = new IgdbClient();
        JSONArray webString = gamesClient.getWebsite(gameInfo.getPictureID());
        String webStrings = "Websites: \n";

        for (int i = 0; i < webString.length(); i++) {
            String temp = webString.getJSONObject(i).getString("url");
            webStrings += temp + "\n";

        }


        tvGameTitle.setText(gameInfo.getTitle());
        tvGameSummary.setText(gameInfo.getSummary());
        tvGameRating.setText(String.format("%.2s/100", gameInfo.getRating()));
        tvWebsite.setText(webStrings);

        String coverURL = "https:" + gamesClient.getCovers(gameInfo.getPictureID());

        Log.i("cover", String.valueOf(coverURL));
        // genres?
        StringBuilder genres = new StringBuilder();
        for (int genre: gameInfo.getGenres()) {
            genres.append(new Genre().getGenre(genre));
            genres.append(", ");
        }
//        genres.delete(genres.length()-2, genres.length()); // strip final comma
        tvGameGenres.setText(genres.toString());
        Glide.with(this).load(coverURL).into(ivImage);
    }
}