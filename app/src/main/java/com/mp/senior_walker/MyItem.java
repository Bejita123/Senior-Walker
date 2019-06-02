package com.mp.senior_walker;


import android.graphics.drawable.Drawable;

public class MyItem {
    private  String uid;
    private String Urlpath;
    private String name;
    private String contents;
    public MyItem(){}

    public MyItem(String uid, String Urlpath, String name, String contents){
        this.uid = uid;
        this.Urlpath = Urlpath;
        this.name = name;
        this.contents = contents;
    }
    public void setUid(String uid){
        this.uid = uid;
    }
    public String getUid(){
        return this.uid;
    }
    public String getUrlpath() {
        return Urlpath;
    }

    public void setUrlpath(String Urlpath) {
        this.Urlpath = Urlpath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


}

