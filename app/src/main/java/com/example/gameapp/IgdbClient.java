package com.example.gameapp;

import android.util.Log;

import java.util.List;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import proto.Game;
import proto.GameResult;

public class IgdbClient{


    public static final String REST_CONSUMER_KEY = "c3vyvocsr3oyvgbip5bm3tp0mchc2w";
    private static String accessToken = "Bearer jz5rzhd0u9hqj14hidasn7lvwip9r";

    public IgdbClient() {

    }

    public static JSONArray getGame() {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/games")
                .header("Client-ID", "c3vyvocsr3oyvgbip5bm3tp0mchc2w")
                .header("Authorization", "Bearer yspmr9vt4n8zo5ujij40yq9dn22tuh")
                .header("Accept", "application/json")
                .body("fields name,category,age_ratings,involved_companies,genres,checksum,rating,first_release_date,cover,summary,dlcs,artworks;sort rating desc; where rating <100; limit 20;")
                .asJson();

        int stat = jsonResponse.getStatus();
        if (stat == 200) {
            JSONObject temp = new JSONObject(jsonResponse);
            JSONArray game = jsonResponse.getBody().getArray();
            Log.i("GAMES", "" + stat);
            Log.i("GAMES", temp.toString());
            Log.i("GAMES1", game.toString());
            return game;
        }
        return null;


    }
    public static String getCovers(int cover) {
        int id = cover;
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/covers")
                .header("Client-ID", "c3vyvocsr3oyvgbip5bm3tp0mchc2w")
                .header("Authorization", "Bearer yspmr9vt4n8zo5ujij40yq9dn22tuh")
                .header("Accept", "application/json")
                .body("fields url; where game = "+id+"; limit 20;")
                .asJson();

        int stat = jsonResponse.getStatus();
        Log.i("GAMES", "" + stat);
        if (stat == 200) {
            JSONObject temp = new JSONObject(jsonResponse);
            JSONArray game = jsonResponse.getBody().getArray();
            String url = game.getJSONObject(0).getString("url");

            Log.i("GAMES", temp.toString());
            Log.i("GAMES1", game.toString());
            Log.i("GAMES", url);
            return url;
        }
        return "";
    }


}
