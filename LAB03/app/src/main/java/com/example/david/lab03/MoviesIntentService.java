package com.example.david.lab03;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class MoviesIntentService extends IntentService {

    private static String EXTRA_QUERY;

    public MoviesIntentService(){
        super("MoviesIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        OmdbFetchr omdbFetchr = new OmdbFetchr("922d558d");
        String query = intent.getStringExtra(EXTRA_QUERY);
        ResultReceiver movieReceiver = intent.getParcelableExtra("receiverTag");
        try {
            List<Movie> movies = omdbFetchr.fetchMovies(query);
            Bundle resultData = new Bundle();
            resultData.putParcelableArrayList("movies",(ArrayList<Movie>)movies);
            movieReceiver.send(0,resultData);
            Log.d("asd","sucessful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Intent newIntent(Context packageContext, String query){
        Intent i = new Intent(packageContext,MoviesIntentService.class);
        i.putExtra(EXTRA_QUERY,query);
        return i;
    }
}