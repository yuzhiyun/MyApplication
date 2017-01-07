package com.sunmeng.myapplication.adapter;

/**
 * Created by yuzhiyun on 2016-12-18.
 */
public class ContentItem {
    private int imgID ;
    private String title ;
    private String time ;
    private String author ;

    public ContentItem(int imgID , String title ,String time ,String author){
        this.imgID = imgID ;
        this.author = author ;
        this.time = time ;
        this.title = title ;
    }

    public int getImgID() {
        return imgID;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }
}
