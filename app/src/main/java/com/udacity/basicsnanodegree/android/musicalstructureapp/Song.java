package com.udacity.basicsnanodegree.android.musicalstructureapp;

class Song {

    private final String artistName;
    private final String songName;

    public Song(String songName, String artistName) {
        this.songName = songName;
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }
}
