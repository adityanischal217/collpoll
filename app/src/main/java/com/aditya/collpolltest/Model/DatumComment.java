package com.aditya.collpolltest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DatumComment implements Serializable {

    @SerializedName("owner")
    @Expose
    private Owner owner;
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("publishDate")
    @Expose
    private String publishDate;
    @SerializedName("message")
    @Expose
    private String message;


    /**
     * No args constructor for use in serialization
     */
    public DatumComment() {
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


    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
