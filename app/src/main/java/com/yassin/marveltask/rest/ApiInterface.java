package com.yassin.marveltask.rest;


import com.yassin.marveltask.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("characters")
    Call<DataResponse> getCharaters(@Query("apikey") String apiKey,
                                    @Query("ts") String ts,
                                    @Query("hash") String hash);
}
