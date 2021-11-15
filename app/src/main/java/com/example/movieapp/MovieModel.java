package com.example.movieapp;

public class MovieModel {
    String movieName;
    String movieDescription;
    String img;
    String rating;
    String release;
    float ratingbar;
    String img2;

    public MovieModel(String movieName, String movieDescription, String img, String rating, String release, float ratingbar,String img2) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.img = img;
        this.rating = rating ;
        this.release = release;
        this.ratingbar = ratingbar;
        this.img2 = img2;
    }

    public MovieModel() {
    }

    public String getMovieName() {
        return movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getImg() {
        return img;
    }

    public String getRating() {
        return rating;
    }

    public String getRelease() {
        return release;
    }

    public float getRatingbar() {
        return ratingbar;
    }

    public String getImg2() {
        return img2;
    }
}
