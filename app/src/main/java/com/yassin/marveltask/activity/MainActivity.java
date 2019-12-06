package com.yassin.marveltask.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yassin.marveltask.R;
import com.yassin.marveltask.adapter.CharactersAdapter;
import com.yassin.marveltask.model.Characters;
import com.yassin.marveltask.model.DataResponse;
import com.yassin.marveltask.rest.ApiClient;
import com.yassin.marveltask.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String API_KEY;
    private static String ts;
    private static String hash;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = findViewById(R.id.characters_recycler_view);
        API_KEY = getString(R.string.api_key);
        ts = getString(R.string.ts);
        hash = getString(R.string.hash);
        mContext = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);

        Call<DataResponse> call = service.getCharaters(API_KEY, ts, hash);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(@NonNull Call<DataResponse> call, @NonNull Response<DataResponse> response) {

                List<Characters> characters = null;
                if (response.body() != null) {
                    characters = response.body().getCharactersResponse().getResults();

                    recyclerView.setAdapter(new CharactersAdapter(mContext, characters));
                }
            }

            @Override
            public void onFailure(@NonNull Call<DataResponse> call, @NonNull Throwable t) {
                Log.e("error", "failure");
            }
        });
    }
}
