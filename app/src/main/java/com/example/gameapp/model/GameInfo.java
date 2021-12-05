package com.example.gameapp.model;

import org.parceler.Parcel;

import java.util.ArrayList;


@Parcel
public class GameInfo {
    String title = "";
    String summary = "";
    int id = 0;
    ArrayList<Integer> genres = new ArrayList<Integer>();
    double rating = 0.0;
    int pictureID = 0;

    // for Parcel
    public GameInfo() {}

    public GameInfo(String name) {
        title = name;
    }

    public GameInfo(String title, String summary, double rating, int pictureID) {
        this.title = title;
        this.summary = summary;
        //this.genres = genres;
        this.rating = rating;
        this.pictureID = pictureID;

    }

    public void setTitle(String name) {
        title = name;
    }

    public void setPictureURL(int pictureID) {
        this.pictureID = pictureID;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenres(ArrayList<Integer> genre) { genres = genre; }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getPictureID() {
        return pictureID;
    }

    public String getSummary() {
        return summary;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getGenres() { return genres; }

    public double getRating() {
        return rating;
    }

    public String getRatingString() {
        return String.format("%.2f/10", this.getRating());
    }
}
