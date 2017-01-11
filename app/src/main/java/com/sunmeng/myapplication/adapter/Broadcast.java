package com.sunmeng.myapplication.adapter;

/**
 * Created by yuzhiyun on 2017-01-07.
 */
public class Broadcast {
    private int srcID = 0 ;
    private String title ;
    private String date ;
    public Broadcast(String title , String date){
        this.title = title ;
        this.date = date ;
    }

    public Broadcast(int imgID , String title ,String date){
        srcID = imgID ;
        this.title  =title ;
        this.date = date ;
    }

    public int getSrcID() {
        return srcID;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
