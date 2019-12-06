package com.yassin.marveltask.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yassin.marveltask.Injection;
import com.yassin.marveltask.R;
import com.yassin.marveltask.adapter.CharactersAdapter;
import com.yassin.marveltask.model.Characters;
import com.yassin.marveltask.view_model.MainViewModel;
import com.yassin.marveltask.view_model.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    CharactersAdapter characterAdapter;
    private List<Characters> characters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView recyclerView = findViewById(R.id.characters_recycler_view);

        mContext = this;
        characterAdapter = new CharactersAdapter(characters);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

//ToDo  : send the repository as a parameter using Injection class
        final MainViewModel mainViewModel = ViewModelProviders.of(this,
                new MainViewModelFactory(Injection.provideCharacterRepository(this)))
                .get(MainViewModel.class);

        mainViewModel.getCharacters().observe(this, characters -> {
            if (characters != null) {
                characterAdapter.addItem(characters);
                recyclerView.setAdapter(characterAdapter);

            } else {
                Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
            }
        });


    }
}
