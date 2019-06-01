package com.example.senior_walker;

import android.widget.EditText;

public class Memberinfo {

    private String name;
    private String age;
    private String phoneNumber;

    public Memberinfo(String name, String age,String phoneNumber){

        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
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

}
