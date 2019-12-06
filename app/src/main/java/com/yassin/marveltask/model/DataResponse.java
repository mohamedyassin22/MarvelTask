package com.yassin.marveltask.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataResponse implements Serializable {
    @SerializedName("data")
    CharactersResponse charactersResponse;

    public CharactersResponse getCharactersResponse() {
        return charactersResponse;
    }

    public void setCharactersResponse(CharactersResponse charactersResponse) {
        this.charactersResponse = charactersResponse;
    }
}
