package com.salem4muk.aptvi.arabic.model;


public class Channel_List {

    public String imageUrl;
    public String categoryId;
    public String channelUrl;
    public String channelname;
    public String channelUrltrue;
    private String mKey;



    public Channel_List() {
    }




    public Channel_List(String mKey,String imageUrl, String categoryId, String channelUrl, String channelname, String channelUrltrue) {
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.channelUrl = channelUrl;
        this.channelname = channelname;
        this.channelUrltrue = channelUrltrue;
        this.mKey = mKey;

    }

    public String getChannelUrltrue() {
        return channelUrltrue;
    }

    public void setChannelUrltrue(String channelUrltrue) {
        this.channelUrltrue = channelUrltrue;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getmKey() {
        return mKey;
    }

    public void setKey(String Key) {
        this.mKey = Key;
    }
}

