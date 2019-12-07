package com.yassin.marveltask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Characters implements Serializable {


    @SerializedName("name")
    private String name;
    @SerializedName("thumbnail")
    private Thumbnail thumbnail;
    @SerializedName("description")
    private String description;
    @SerializedName("comics")
    private Comics comics;
    @SerializedName("series")
    private Comics series;
    @SerializedName("stories")
    private Comics stories;
    @SerializedName("events")
    private Comics events;

    private String posterPath;

    public Characters(String name, Thumbnail thumbnail, String description,
                      Comics comics) {
        this.name = name;
        this.thumbnail = thumbnail;
        this.description = description;
        this.comics = comics;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public Comics getSeries() {
        return series;
    }

    public void setSeries(Comics series) {
        this.series = series;
    }

    public Comics getStories() {
        return stories;
    }

    public void setStories(Comics stories) {
        this.stories = stories;
    }

    public Comics getEvents() {
        return events;
    }

    public void setEvents(Comics events) {
        this.events = events;
    }
}
