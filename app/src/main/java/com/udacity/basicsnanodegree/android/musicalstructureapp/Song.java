package com.udacity.basicsnanodegree.android.musicalstructureapp;

import android.os.Parcel;
import android.os.Parcelable;

class Song implements Parcelable {

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
    private final String artistName;
    private final String songName;
    private final int duration;

    /**
     * @param songName
     * @param artistName
     * @param duration   Duration in seconds.
     */
    public Song(String songName, String artistName, int duration) {
        this.songName = songName;
        this.artistName = artistName;
        this.duration = duration;
    }

    public Song(Parcel in) {
        artistName = in.readString();
        songName = in.readString();
        duration = in.readInt();
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(artistName);
        dest.writeString(songName);
        dest.writeInt(duration);
    }
}
