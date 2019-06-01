package com.example.senior_walker;

public class Petinfo {

    private String dog;
    private String height;
    private String weight;

    public Petinfo(){}
    public Petinfo(String dog, String height,String weight){

        this.dog = dog;
        this.height = height;
        this.weight = weight;
    }

    public String getDog(){
        return this.dog;
    }
    public void setDog(String dog){
        this.dog = dog;
    }
    public String getHeight(){
        return this.height;
    }
    public void setHeight(String height){
        this.height = height;
    }
    public String getWeight(){
        return this.weight;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
}
