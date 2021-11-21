package com.example.gameapp;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ParseData extends Activity {
    public static JSONArray getJsonFromAssets(Context context, String fileName) throws JSONException {
        String jsonString;
        fileName = "data/Games.JSON";
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonData(jsonString);
    }
    private static JSONArray jsonData(String jsonData) throws JSONException {
        final JSONObject obj = new JSONObject(jsonData);
        final JSONArray games = obj.getJSONArray("");
        return games;
    }

}
