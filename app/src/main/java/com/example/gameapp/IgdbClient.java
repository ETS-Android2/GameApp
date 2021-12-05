package com.example.gameapp;

import android.util.Log;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class IgdbClient{


    public static final String REST_CONSUMER_KEY = "c3vyvocsr3oyvgbip5bm3tp0mchc2w";
    private static String accessToken = "Bearer jz5rzhd0u9hqj14hidasn7lvwip9r";

    public IgdbClient() {

    }

    public JSONArray getGamesInfo() {
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
            Log.i("GAMES", game.toString());
            return game;
        }
        return null;


    }
    public String getCovers(int cover) {
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
            try {
            JSONObject temp = new JSONObject(jsonResponse);
            JSONArray game = jsonResponse.getBody().getArray();
            String url = game.getJSONObject(0).getString("url");

            Log.i("GAMES1", temp.toString());
            Log.i("GAMES1", game.toString());
            Log.i("GAMES1", url);
            return url;
            } catch (Exception e) {
            e.printStackTrace();
        }
        }
        return "";
    }
    public JSONArray getSearch(String game) {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/games")
                .header("Client-ID", "c3vyvocsr3oyvgbip5bm3tp0mchc2w")
                .header("Authorization", "Bearer yspmr9vt4n8zo5ujij40yq9dn22tuh")
                .header("Accept", "application/json")
                .body("fields name,category,age_ratings,involved_companies,genres,checksum,rating,first_release_date,cover,summary,dlcs,artworks; where name=\""+ game + "\";")
                .asJson();

        int stat = jsonResponse.getStatus();
        Log.i("GAMES2", "" + stat);
        if (stat == 200) {
            JSONObject temp = new JSONObject(jsonResponse);
            JSONArray games = jsonResponse.getBody().getArray();
            Log.i("GAMES2", "" + stat);
            Log.i("GAMES2", temp.toString());
            Log.i("GAMES2", games.toString());
            return games;
        }
        return null;
    }
    public JSONArray getGenreGame(int genreId) {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/games")
                .header("Client-ID", "c3vyvocsr3oyvgbip5bm3tp0mchc2w")
                .header("Authorization", "Bearer yspmr9vt4n8zo5ujij40yq9dn22tuh")
                .header("Accept", "application/json")
                .body("fields name,category,age_ratings,involved_companies,genres,checksum,rating,first_release_date,cover,summary,dlcs,artworks;sort rating desc; where genres="+ genreId +"; limit 20;")
                .asJson();

        int stat = jsonResponse.getStatus();
        if (stat == 200) {
            JSONObject temp = new JSONObject(jsonResponse);
            JSONArray game = jsonResponse.getBody().getArray();
            Log.i("GAMES3", "" + stat);
            Log.i("GAMES3", temp.toString());
            Log.i("GAMES3", game.toString());
            return game;
        }
        return null;
    }
    public JSONArray getWebsite(int cover) {
        int id = cover;
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.igdb.com/v4/websites")
                .header("Client-ID", "c3vyvocsr3oyvgbip5bm3tp0mchc2w")
                .header("Authorization", "Bearer yspmr9vt4n8zo5ujij40yq9dn22tuh")
                .header("Accept", "application/json")
                .body("fields url; where game = "+id+"; limit 20;")
                .asJson();

        int stat = jsonResponse.getStatus();
        Log.i("GAMES", "" + stat);
        if (stat == 200) {
            try {
                JSONObject temp = new JSONObject(jsonResponse);
                JSONArray game = jsonResponse.getBody().getArray();
                String url = game.getJSONObject(0).getString("url");

                Log.i("GAMES5", temp.toString());
                Log.i("GAMES5", game.toString());
                Log.i("GAMES5", url);
                return game;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}
