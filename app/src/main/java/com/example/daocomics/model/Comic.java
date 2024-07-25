package com.example.daocomics.model;

import java.io.Serializable;

public class Comic implements Serializable {

    String name;
    String desciption;
    String imageThumb;
    String genre;
    public Comic( String name, String desciption, String imageThumb, String genre) {
        this.name = name;
        this.desciption = desciption;
        this.imageThumb = imageThumb;
        this.genre = genre;
    }
    public Comic() {
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


}
