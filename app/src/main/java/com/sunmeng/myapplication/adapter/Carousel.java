package com.sunmeng.myapplication.adapter;

/**
 * Created by yuzhiyun on 2017-01-07.
 */
public class Carousel {
    //轮播图的id编号
    private int index;
    // 轮播图中的图片的url
    private String imgUrl;
    //轮播图所对应的链接
    private String url;

    public Carousel(int index , String imgUrl , String url){
        this.index = index ;
        this.imgUrl = imgUrl ;
        this.url = url ;
    }
    public int getIndex() {
        return index;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getUrl() {
        return url;
    }
}
