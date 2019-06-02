package com.mp.senior_walker.info;

public class Memberinfo {

    private String name;
    private String age;
    private String phoneNumber;
    private String photoUrl;

    public Memberinfo(){}
    public Memberinfo(String photoUrl){
        this.photoUrl = photoUrl;
    }

    public Memberinfo(String name, String age,String phoneNumber){
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;

    }

    public Memberinfo(String name, String age,String phoneNumber, String photoUrl){

        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.photoUrl = photoUrl;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAge(){
        return this.age;
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }
}
