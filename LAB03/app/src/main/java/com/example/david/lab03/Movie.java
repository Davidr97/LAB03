package com.example.david.lab03;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{

    String Title;
    String Year;
    String Poster;

    protected Movie(Parcel in) {
        Title = in.readString();
        Year = in.readString();
        Poster = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Year);
        dest.writeString(Poster);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}