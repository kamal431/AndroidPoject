package com.example.contactapp;

public class List_Item {
    private String name;
    private String desc;
    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List_Item(String name, String desc, String image){
        this.name = name;
        this.desc = desc;
        this.image = image;
    }
}

// File myObj = new File("filename.txt");
