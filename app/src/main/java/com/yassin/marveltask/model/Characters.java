package com.yassin.marveltask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Characters implements Serializable {


    @SerializedName("name")
    private String name;
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;

    private String posterPath;

    public Characters(String name, Thumbnail thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPosterPath() {
        return getThumbnail().path + "." + getThumbnail().extension;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


}
