package com.example.gameapp.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Genre")
public class Genre extends ParseObject {

    public static final String KEY_NAME = "name";

    public String getName(int id) {
        return getString(KEY_NAME);
    }
}
