package com.yassin.marveltask.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharactersResponse {

    @SerializedName("count")
    private int count;
    @SerializedName("results")
    private List<Characters> results;
    @SerializedName("total")
    private int total;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Characters> getResults() {
        return results;
    }

    public void setResults(List<Characters> results) {
        this.results = results;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
