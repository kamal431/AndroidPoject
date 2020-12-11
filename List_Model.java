package com.example.contactapp;

import android.media.Image;

import io.realm.RealmObject;

public class List_Model extends RealmObject {
    private String name;
    private String desc;
    private int imageID;
    private String favourite_of;

    public String getFavourite_of() {
        return favourite_of;
    }

    public void setFavourite_of(String favourite_of) {
        this.favourite_of = favourite_of;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
