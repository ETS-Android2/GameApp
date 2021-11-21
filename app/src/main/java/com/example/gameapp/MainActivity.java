package com.example.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.api.igdb.exceptions.RequestException;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        JSONArray datas = null;
        try {
            datas = ParseData.getJsonFromAssets(getApplicationContext(), "Games.JSON");
            Log.i("data", datas.getJSONObject(0).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}