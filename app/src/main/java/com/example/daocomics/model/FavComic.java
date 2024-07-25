package com.example.daocomics.model;

public class FavComic {
    String ComicName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public FavComic(String comicName, String usName,String id) {
        ComicName = comicName;
        UsName = usName;
        this.id = id;
    }

    public FavComic() {
    }

    public String getComicName() {
        return ComicName;
    }

    public void setComicName(String comicName) {
        ComicName = comicName;
    }

    public String getUsName() {
        return UsName;
    }

    public void setUsName(String usName) {
        UsName = usName;
    }

    String UsName;
}
