package com.yassin.marveltask.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yassin.marveltask.Injection;
import com.yassin.marveltask.R;
import com.yassin.marveltask.adapter.CharactersAdapter;
import com.yassin.marveltask.adapter.SearchCharactersAdapter;
import com.yassin.marveltask.model.Characters;
import com.yassin.marveltask.view_model.MainViewModel;
import com.yassin.marveltask.view_model.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    CharactersAdapter characterAdapter;
    private List<Characters> characters = new ArrayList<>();
    Toolbar toolbar;
    TextView toolbarTitle;
    RecyclerView recyclerView, recyclerSearch;
    private SearchCharactersAdapter searchCharactersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.characters_recycler_view);
        recyclerSearch = findViewById(R.id.search_recycler_view);
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        toolbarTitle.setText("MARVEL");
        getSupportActionBar().setTitle("");
        mContext = this;
        characterAdapter = new CharactersAdapter(characters);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        GridLayoutManager searchGridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);

        recyclerSearch.setLayoutManager(searchGridLayoutManager);
        recyclerSearch.setItemAnimator(new DefaultItemAnimator());

//ToDo  : send the repository as a parameter using Injection class
        final MainViewModel mainViewModel = ViewModelProviders.of(this,
                new MainViewModelFactory(Injection.provideCharacterRepository(this)))
                .get(MainViewModel.class);

        mainViewModel.getCharacters().observe(this, characters -> {
            if (characters != null) {
                characterAdapter.addItem(characters);
                searchCharactersAdapter = new SearchCharactersAdapter(this, (ArrayList<Characters>) characters);
                recyclerView.setAdapter(characterAdapter);


            } else {
                Toast.makeText(mContext, R.string.error, Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        ImageView closeButton = mSearchView.findViewById(R.id.search_close_btn);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (!query.equals("") || !query.isEmpty()) {
                    searchCharactersAdapter.getFilter().filter(query);
                    recyclerSearch.setAdapter(searchCharactersAdapter);
                    recyclerSearch.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });

        // Set on click listener
        closeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mSearchView.setQuery("", false);
                //Collapse the action view
                mSearchView.onActionViewCollapsed();
                //Collapse the search widget
                mSearch.collapseActionView();
                recyclerSearch.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_search:
                recyclerView.setVisibility(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
