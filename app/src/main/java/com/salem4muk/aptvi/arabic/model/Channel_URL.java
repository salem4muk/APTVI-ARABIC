package com.salem4muk.aptvi.arabic.model;

public class Channel_URL {

    public String name_url;
    public String channelurltype;

    public Channel_URL() {
    }

    public Channel_URL(String name_url, String channelurltype) {
        this.name_url = name_url;
        this.channelurltype = channelurltype;
    }

    public String getName_url() {
        return name_url;
    }

    public void setName_url(String name_url) {
        this.name_url = name_url;
    }

    public String getChannelurltype() {
        return channelurltype;
    }

    public void setChannelurltype(String channelurltype) {
        this.channelurltype = channelurltype;
    }
}
