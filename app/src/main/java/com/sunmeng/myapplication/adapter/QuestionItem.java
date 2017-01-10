package com.sunmeng.myapplication.adapter;

/**
 * Created by yuzhiyun on 2017-01-10.
 */
public class QuestionItem {
    private int imgID ;
    private String content ;
    public QuestionItem(int imgID , String content){
        this.imgID = imgID ;
        this.content = content ;
    }

    public int getImgID() {
        return imgID;
    }

    public String getContent() {
        return content;
    }
}
