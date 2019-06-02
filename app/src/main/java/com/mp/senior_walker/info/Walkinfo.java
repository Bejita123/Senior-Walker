package com.mp.senior_walker.info;

public class Walkinfo {
    String date;
    String time;
    String location;
    String walker;
    String Owner;

    public Walkinfo(){
    }
    public Walkinfo(String date, String time, String location, String Walker, String Owner){
        this.date = date;
        this.time = time;
        this.location = location;
        this.walker = Walker;
        this.Owner = Owner;
    }
    public Walkinfo(String date, String time, String location, String Owner){
        this.date = date;
        this.time = time;
        this.location = location;
        this.Owner = Owner;
    }
    public Walkinfo(String date, String time, String location){
        this.date = date;
        this.time = time;
        this.location = location;
    }
    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return this.time;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setWalker(String walker){
        this.walker = walker;
    }
    public String getWalker(){
        return this.walker;
    }
    public void setOwner(String Owner){
        this.Owner = Owner;
    }
    public String getOwner(){
        return this.Owner;
    }
}
