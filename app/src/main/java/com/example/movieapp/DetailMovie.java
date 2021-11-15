package com.example.movieapp;

import static com.example.movieapp.MainActivity.EXTRA_DESCRIPTION;
import static com.example.movieapp.MainActivity.EXTRA_NAME;
import static com.example.movieapp.MainActivity.EXTRA_RATING;
import static com.example.movieapp.MainActivity.EXTRA_RATINGBAR;
import static com.example.movieapp.MainActivity.EXTRA_RELEASE;
import static com.example.movieapp.MainActivity.EXTRA_URL;
import static com.example.movieapp.MainActivity.EXTRA_URLIMG2;
//import static com.example.movieapp.MainActivity.EXTRA_URL2;

import static java.lang.Float.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailMovie extends AppCompatActivity {

    String url = "https://themoviedb.org/t/p/w500";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String movieName = intent.getStringExtra(EXTRA_NAME);
        String movieDescription = intent.getStringExtra(EXTRA_DESCRIPTION);
        String rating = intent.getStringExtra(EXTRA_RATING);
        String release = intent.getStringExtra(EXTRA_RELEASE);
        String backdropImage = intent.getStringExtra(EXTRA_URLIMG2);
        //Float ratingbar = Float.valueOf(intent.getStringExtra(EXTRA_RATINGBAR));

        ImageView imageView = findViewById(R.id.image_view_detail);
        ImageView imageView1 = findViewById(R.id.image_backdrop_detail);
        TextView textViewName = findViewById(R.id.movie_detail_txt);
        TextView textViewDescription = findViewById(R.id.description_detail_txt);
        TextView textViewRating = findViewById(R.id.rating_detail_txt);
        TextView textViewRelease = findViewById(R.id.release_detail_txt);
        RatingBar ratingBar = findViewById(R.id.ratingbar_detail);

        Glide.with(this)
                .load(url + imageUrl)
                .fitCenter()
                .into(imageView);

        Glide.with(this)
                .load(url + backdropImage)
                .fitCenter()
                .into(imageView1);

        textViewName.setText(movieName);
        textViewDescription.setText(movieDescription);
        textViewRating.setText(rating);
        textViewRelease.setText(release);
        //ratingBar.setRating(ratingbar);
    }
}