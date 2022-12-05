/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import GUI.*;

/**
 *
 * @author PC
 */
public class SongDTO {

    private String title;
    private String artist;
    private String iconName;
    private String lyrics;
    private String stream;

    public SongDTO(String title, String artist, String iconName, String lyrics, String stream) {
        super();
        this.title = title;
        this.artist = artist;
        this.iconName = iconName;
        this.lyrics = lyrics;
        this.stream = stream;
    }

    public SongDTO() {

    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    @Override
    public String toString() {
        return title + " - " + artist + " - " + iconName + " - " + lyrics + " - " + stream;
    }
}
