package com.mp.senior_walker.info;

public class Walkinfo {
    String date;
    String time;
    String location;

    public Walkinfo(){
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
}
