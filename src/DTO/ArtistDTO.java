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
public class ArtistDTO {

    private String image;
    private String nameArtist;
    private String birthday;
    private String gender;
    private String country;
    private String infolife;
    private String songTop;
    private String album;

    public ArtistDTO(String image, String nameArtist, String birthday, String gender, String country, String infolife, String songTop, String album) {
        this.image = image;
        this.nameArtist = nameArtist;
        this.birthday = birthday;
        this.gender = gender;
        this.country = country;
        this.infolife = infolife;
        this.songTop = songTop;
        this.album = album;
    }

    public ArtistDTO() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInfolife() {
        return infolife;
    }

    public void setInfolife(String infolife) {
        this.infolife = infolife;
    }

    public String getSongTop() {
        return songTop;
    }

    public void setSongTop(String songTop) {
        this.songTop = songTop;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

}
