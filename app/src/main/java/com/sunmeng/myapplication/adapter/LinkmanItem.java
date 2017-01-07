package com.sunmeng.myapplication.adapter;

/**
 * Created by yuzhiyun on 2016-12-18.
 */
public class LinkmanItem {
    private int imgID ;
    private String name ;
    private String dscrp ;

    public LinkmanItem(int imgID , String name , String dscrp){
        this.imgID = imgID;
        this.name = name;
        this.dscrp = dscrp;
    }
    public int getImgID() {
        return imgID;
    }

    public String getName() {
        return name;
    }

    public String getDscrp() {
        return dscrp;
    }
}
