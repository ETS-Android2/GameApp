package com.example.gameapp.model;

import com.example.gameapp.IgdbClient;

import java.util.HashMap;
import java.util.Map;

import kong.unirest.json.JSONArray;

public class Genre {
    private static final Map<Integer, String> genres = new HashMap<Integer, String>() {{
       put(2, "Point-and-Click");
       put(4, "Fighting");
       put(5, "Shooter");
       put(7, "Music");
       put(8, "Platform");
       put(9, "Puzzle");
       put(10, "Racing");
       put(11, "RTS");
       put(12, "RPG");
       put(13, "Simulator");
       put(14, "Sport");
       put(15, "Strategy");
       put(16, "TBS");
       put(24, "Tactical");
       put(25, "Hack & Slash");
       put(26, "Trivia");
       put(30, "Pinball");
       put(31, "Adventure");
       put(32, "Indie");
       put(33, "Arcade");
       put(34, "Visual Novel");
       put(35, "Card & Board Games");
       put(36, "MOBA");
    }};
    private static final int[] genreKey = new int[]{2,4,5,7,8,9,10,11,12,13,14,15,16,24,25,26,30,31,32,33,34,35,36};

    public String getGenre(int id) {
        return genres.get(id);
    }
    public int[] getGenreKey() {
       return genreKey;
   }
   public JSONArray getGenreGamesList(int id)
   {
      IgdbClient gamesClient = new IgdbClient();
      return(gamesClient.getGenreGame(id));
   }
}
