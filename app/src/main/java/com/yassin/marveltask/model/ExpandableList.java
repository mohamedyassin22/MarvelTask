package com.yassin.marveltask.model;

import java.util.List;

public class ExpandableList {

    List<ComicsItems> comicsItem;
    private String header;

    public ExpandableList(String header, List<ComicsItems> comicsItem) {
        this.header = header;
        this.comicsItem = comicsItem;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<ComicsItems> getComicsItem() {
        return comicsItem;
    }

    public void setComicsItem(List<ComicsItems> comicsItem) {
        this.comicsItem = comicsItem;
    }
}
