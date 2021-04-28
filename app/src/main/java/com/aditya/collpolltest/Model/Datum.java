package com.aditya.collpolltest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Datum implements Serializable {

    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("link")
    @Expose
    private Object link;
    @SerializedName("likes")
    @Expose
    private Integer likes;


    /**
     * No args constructor for use in serialization
     */
    public Datum() {
    }


    public Datum(Owner owner, String id, String image, String publishDate, String text, List<String> tags, Object link, Integer likes) {
        super();
        this.owner = owner;
        this.id = id;
        this.image = image;
        this.publishDate = publishDate;
        this.text = text;
        this.tags = tags;
        this.link = link;
        this.likes = likes;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getLink() {
        return link;
    }

    public void setLink(Object link) {
        this.link = link;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

}
