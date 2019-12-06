package com.yassin.marveltask.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://gateway.marvel.com:443/v1/public/";
    private static Retrofit retofit = null;

    public static Retrofit getClient() {
        if (retofit == null) {
            retofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retofit;
    }

}
