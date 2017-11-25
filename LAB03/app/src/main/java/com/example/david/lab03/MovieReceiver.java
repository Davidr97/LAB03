package com.example.david.lab03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MovieReceiver extends ResultReceiver {


    private MovieAdapter adapter;
    private Context context;

    public MovieReceiver(Handler handler) {
        super(handler);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if(resultCode == 0){
            List<Movie> movies = resultData.getParcelableArrayList("movies");
            adapter.setMovies(movies);
            adapter.notifyDataSetChanged();
        }
        else if(resultCode == 1){
            Toast.makeText(context,"asd",Toast.LENGTH_SHORT).show();
            MovieDetails movieDetails = resultData.getParcelable("moviedetails");
            Intent i = new Intent(context,MovieDetailsActivity.class);
            Log.d("start","Activity is started");
            context.startActivity(i);
        }
    }

    public MovieAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(MovieAdapter adapter) {
        this.adapter = adapter;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}