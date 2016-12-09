package com.TechScope.nauman.pakchinanews;

import java.io.Serializable;

/**
 * Created by NAUMAN on 8/18/2016.
 */
public class News implements Serializable {
    private String Title;
    private String Description;
    private String Date;
    private int PictureId;
    private String newsType;
    private String PictureName;
    private String Caption;
    private String post_url;

    public News(String title, String pictureName, String newsType, int pictureId, String date, String description, String caption, String Post_url) {
        Title = title;
        PictureName = pictureName;
        this.newsType = newsType;
        PictureId = pictureId;
        Date = date;
        Description = description;
        Caption = caption;
        post_url = Post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    public String getPost_url() {
        return post_url;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public void setPictureName(String pictureName) {
        PictureName = pictureName;
    }

    public String getPictureName() {
        return PictureName;
    }

    public String getNewsType() {
        return newsType;
    }

    public int getPictureId() {
        return PictureId;
    }

    public String getDate() {
        return Date;
    }

    public String getDescription() {
        return Description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public void setPictureId(int pictureId) {
        PictureId = pictureId;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
