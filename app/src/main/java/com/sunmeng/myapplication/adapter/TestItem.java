package com.sunmeng.myapplication.adapter;

/**
 * Created by yuzhiyun on 2017-01-09.
 */
public class TestItem {
    private int imgID ;
    private String title ;
    private String money ;
    private String number ;
    public TestItem(int imgID , String title , String money , String number){
        this.imgID = imgID ;
        this.title = title ;
        this.money = money ;
        this.number = number ;
    }

    public int getImgID() {
        return imgID;
    }

    public String getTitle() {
        return title;
    }

    public String getMoney() {
        return money;
    }

    public String getNumber() {
        return number;
    }
}

