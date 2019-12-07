package com.yassin.marveltask.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yassin.marveltask.R;
import com.yassin.marveltask.adapter.DetailsExpandableListAdapter;
import com.yassin.marveltask.model.Characters;
import com.yassin.marveltask.model.ExpandableList;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    ImageView ivCharacterPoster;
    TextView tvName, tvDescription;
    ExpandableListView lv;
    List<ExpandableList> expandableLists = new ArrayList<>();
    DetailsExpandableListAdapter listAdapter;
    private Context mContext;

    public static void start(Context context, Characters characters) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("character", characters);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initialView();
        mContext = this;
        Intent intent = getIntent();
        if (intent != null) {
            Characters characters = (Characters) intent.getSerializableExtra("character");
            String image_url = characters.getPosterPath();
            Glide.with(getApplicationContext()).load(image_url).into(ivCharacterPoster);
//            ivCharacterPoster.setImageIcon(R.drawable.ic_launcher_foreground);
            tvName.setText(characters.getName());
            if (characters.getDescription().equals("")) {
                tvDescription.setText(getResources().getString(R.string.not));

            } else {
                tvDescription.setText(characters.getDescription());

            }
            ExpandableList comics = new ExpandableList("COMICS",
                    characters.getComics().getItems());
            expandableLists.add(comics);
            listAdapter = new DetailsExpandableListAdapter(mContext,
                    expandableLists);
            listAdapter.notifyDataSetChanged();
            lv.setAdapter(listAdapter);
            lv.setVisibility(View.VISIBLE);

        }


    }

    void initialView() {
        lv = findViewById(R.id.lvExp);
        ivCharacterPoster = findViewById(R.id.characterPoster);
        tvName = findViewById(R.id.tv_name);
        tvDescription = findViewById(R.id.tv_description);
    }
}
