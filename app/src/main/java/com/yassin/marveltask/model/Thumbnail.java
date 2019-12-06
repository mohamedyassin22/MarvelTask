package com.yassin.marveltask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Thumbnail implements Serializable {

    @SerializedName("path")
    String path;

    @SerializedName("extension")
    String extension;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
