package com.example.movieapp;

import static com.example.movieapp.MainActivity.EXTRA_BACKDROPIMAGE;
import static com.example.movieapp.MainActivity.EXTRA_DESCRIPTION;
import static com.example.movieapp.MainActivity.EXTRA_NAME;
import static com.example.movieapp.MainActivity.EXTRA_POSTERIMAGE;
import static com.example.movieapp.MainActivity.EXTRA_RATING;
import static com.example.movieapp.MainActivity.EXTRA_RELEASE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class DetailMovie extends AppCompatActivity {

    String url = "https://themoviedb.org/t/p/w500";
    String videoUrl = "https://www572.ff-02.com/token=EuFImysOQTwsi3iZ1TUYBw/1638050626/182.253.0.0/4/6/91/bef194d75fe07d7fd684028a32b4b916-1080p.mp4";

    PlayerView playerView;
    ProgressBar progressBar;
    ImageView btFullscreen;
    SimpleExoPlayer simpleExoPlayer;
    Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        playerView = findViewById(R.id.player_view);
        progressBar = findViewById(R.id.progress_bar);
        btFullscreen = findViewById(R.id.bt_fullscreen);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_POSTERIMAGE);
        String movieName = intent.getStringExtra(EXTRA_NAME);
        String movieDescription = intent.getStringExtra(EXTRA_DESCRIPTION);
        String rating = intent.getStringExtra(EXTRA_RATING);
        String release = intent.getStringExtra(EXTRA_RELEASE);
        String backdropImage = intent.getStringExtra(EXTRA_BACKDROPIMAGE);
        float ratingbar = Float.parseFloat(rating);

        ImageView imageView = findViewById(R.id.image_view_detail);
        //ImageView imageView1 = findViewById(R.id.image_backdrop_detail);
        TextView textViewName = findViewById(R.id.movie_detail_txt);
        TextView textViewDescription = findViewById(R.id.description_detail_txt);
        TextView textViewRating = findViewById(R.id.rating_detail_txt);
        TextView textViewRelease = findViewById(R.id.release_detail_txt);
        RatingBar ratingBar = findViewById(R.id.ratingbar_detail);

        Glide.with(this)
                .load(url + imageUrl)
                .fitCenter()
                .into(imageView);

//        Glide.with(this)
//                .load(url + backdropImage)
//                .fitCenter()
//                .into(imageView1);

        textViewName.setText(movieName);
        textViewDescription.setText(movieDescription);
        textViewRating.setText(rating);
        textViewRelease.setText(release);
        ratingBar.setRating(ratingbar/2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Uri uri = Uri.parse(videoUrl);
        LoadControl loadControl = new DefaultLoadControl();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this,trackSelector,loadControl);
        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(uri, factory,extractorsFactory,null,null);
        playerView.setPlayer(simpleExoPlayer);
        playerView.setKeepScreenOn(true);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if (playbackState == Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);
                }else if (playbackState == Player.STATE_READY){
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });

        btFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen));

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    flag = false;
                }else {
                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_bullscreen_exit));

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                    flag = true;
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }
}