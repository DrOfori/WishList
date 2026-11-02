package com.example.model;
import java.time.LocalDateTime;

public class Wishlist {
    private int id;
    private String name;
    private LocalDateTime createdAt;

    public Wishlist(){}

    public Wishlist(int id, String name, LocalDateTime createdAt){
        this.id=id;
        this.name=name;
        this.createdAt=createdAt;

    }
    //getter
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    //setter
    public void setId(int newId){
        this.id=newId;
    }
    public void setName(String newName){
        this.name=newName;
    }
    public void setCreatedAt(LocalDateTime newCreatedAt){
        this.createdAt=newCreatedAt;
    }


    public String toString(){
        return "ID: "+this.id+"\n Name: "+this.name+"\n Created at this time: "+this.createdAt;
    }
}
