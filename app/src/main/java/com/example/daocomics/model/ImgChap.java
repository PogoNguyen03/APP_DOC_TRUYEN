package com.example.daocomics.model;

public class ImgChap {
    String comicName;
    String chapterName;
    String url;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    int page;

    public ImgChap(String comicName, String chapterName, String url,int page) {
        this.comicName = comicName;
        this.chapterName = chapterName;
        this.url = url;
        this.page = page;
    }

    public ImgChap() {
    }

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
