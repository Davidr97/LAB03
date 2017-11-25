package com.example.david.lab03;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.IOException;


public class MovieDetailsIntentService extends IntentService {

    private static String EXTRA_TITLE;

    public MovieDetailsIntentService(){
        super("MovieDetailsIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        OmdbFetchr omdbFetchr = new OmdbFetchr("922d558d");
        String title = intent.getStringExtra(EXTRA_TITLE);
        try {
            MovieDetails movieDetails = omdbFetchr.fetchMovieDetails(title);
            if(movieDetails !=null){
                Log.d("Successfully fetched","Successful fetch");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Intent newIntent(Context packageContext, String title){
        Intent i = new Intent(packageContext,MoviesIntentService.class);
        i.putExtra(EXTRA_TITLE,title);
        return i;
    }
}