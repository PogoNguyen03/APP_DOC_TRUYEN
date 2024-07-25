package com.example.daocomics.model;

import java.io.Serializable;

public class Chapter implements Serializable {
    String chapterName;

    public String getNextChap() {
        return nextChap;
    }

    public void setNextChap(String nextChap) {
        this.nextChap = nextChap;
    }

    public String getPreChap() {
        return preChap;
    }

    public void setPreChap(String preChap) {
        this.preChap = preChap;
    }

    String nextChap;
    String preChap;

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    String comicName;


    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Chapter() {
    }

    public Chapter(String chapterName,String comicName,String nextChap,String preChap) {
        this.chapterName = chapterName;
        this.comicName = comicName;
        this.nextChap = nextChap;
        this.preChap = preChap;
    }
}
