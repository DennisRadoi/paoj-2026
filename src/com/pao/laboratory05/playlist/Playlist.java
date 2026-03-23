package com.pao.laboratory05.playlist;
import com.pao.laboratory05.playlist.Song;

import java.util.Arrays;

public class Playlist {
    private String name;
    private Song[] songs;

    public Playlist(String name){
        this.name = name;
        this.songs = new Song[0];
    }
    public String getName() {
        return name;
    }
    public void addSong(Song song){
        Song[] newSongs = new Song[this.songs.length + 1];
        System.arraycopy(songs, 0, newSongs, 0, songs.length);
        newSongs[songs.length] = song;
        this.songs = newSongs;
    }

    public void printSortedByTitle(){
        Song[] copy = songs.clone();
        Arrays.sort(copy);

        for(Song s : copy){
            System.out.println(s);
        }
    }

    public void printSortedByDuration(){
        Song[] copy = songs.clone();
        Arrays.sort(copy, new SongDurationComparator());

        for(Song s : copy){
            System.out.println(s);
        }
    }

    public int getTotalDuration(){
        int suma = 0;
        for(Song s : this.songs){
            suma += s.durationSeconds();
        }
        return suma;
    }

}
