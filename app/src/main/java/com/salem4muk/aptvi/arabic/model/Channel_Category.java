package com.salem4muk.aptvi.arabic.model;

public class Channel_Category {

    String name;
    String link_image;




    public Channel_Category() {

    }


    public Channel_Category(String name, String link_image) {
        this.name = name;
        this.link_image = link_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink_image() {
        return link_image;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image;
    }
}
