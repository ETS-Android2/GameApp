package com.example.gameapp;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("I4MmR5qVYovAU56hKNPLrRJrM9FAZbMvu4Aa5XGw")
                .clientKey("bV79ScwtzfelO2EOsKZN3NyXK4XcKuqLBMhsWHQk")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
