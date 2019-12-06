package com.yassin.marveltask.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yassin.marveltask.model.Characters;
import com.yassin.marveltask.model.DataResponse;
import com.yassin.marveltask.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//ToDo 2: Implement Character Repository to fetch data from network
public class CharacterRepositoryImpl implements CharacterRepository {

    private ApiInterface service;
    private String apiKey;
    private String ts;
    private String hash;

    public CharacterRepositoryImpl(ApiInterface service,
                                   String apiKey, String ts, String hash) {
        this.service = service;
        this.apiKey = apiKey;
        this.ts = ts;
        this.hash = hash;
    }

    //ToDo : implement get Character

    @Override
    public LiveData<List<Characters>> getCharacter() {
        final MutableLiveData<List<Characters>> characters = new MutableLiveData<>();

        Call<DataResponse> call = service.getCharaters(apiKey, ts, hash);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(@NonNull Call<DataResponse> call, @NonNull Response<DataResponse> response) {

                if (response.body() != null) {
                    characters.setValue(response.body().getCharactersResponse().getResults());

                } else {
                    characters.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataResponse> call, @NonNull Throwable t) {
                characters.setValue(null);
            }
        });
        return characters;
    }
}
