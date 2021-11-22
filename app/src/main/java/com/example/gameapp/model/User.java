package com.example.gameapp.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public String getUsername(int id) {
        return getString(KEY_USERNAME);
    }

    public String getPassword(int id) {
        return getString(KEY_PASSWORD);
    }
}
