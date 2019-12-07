package com.yassin.marveltask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Comics implements Serializable {

    @SerializedName("items")
    List<ComicsItems> items;

    public List<ComicsItems> getItems() {
        return items;
    }

    public void setItems(List<ComicsItems> items) {
        this.items = items;
    }
}
