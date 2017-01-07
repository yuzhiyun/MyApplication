package com.sunmeng.myapplication.adapter;

/**
 * Created by yuzhiyun on 2016-12-11.
 */
public class ChatItem {
    private int imgID ;
    private String title ;
    private String time ;
    private String area ;
    private String src ;
    public ChatItem(int imgID , String title , String time ,String area ,String src){
        this.imgID = imgID ;
        this.title = title ;
        this.time = time ;
        this.area = area ;
        this.src = src ;
    }

    public int getImgID() {
        return imgID;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getArea() {
        return area;
    }

    public String getSrc() {
        return src;
    }
}
