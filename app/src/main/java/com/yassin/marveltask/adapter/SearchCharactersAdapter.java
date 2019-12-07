package com.yassin.marveltask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yassin.marveltask.R;
import com.yassin.marveltask.activity.DetailsActivity;
import com.yassin.marveltask.model.Characters;

import java.util.ArrayList;
import java.util.List;


public class SearchCharactersAdapter extends
        RecyclerView.Adapter<SearchCharactersAdapter.CharactersViewHolder> implements Filterable {


    ArrayList<Characters> characters, filterList;
    CustomFilter filter;
    private Context context;

    public SearchCharactersAdapter(Context context, ArrayList<Characters> characters) {
        this.context = context;
        this.characters = characters;
        this.filterList = characters;

    }

    @NonNull
    @Override
    public SearchCharactersAdapter.CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.search_list_item, parent, false);

        return new CharactersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CharactersViewHolder holder, final int position) {


        Characters character = characters.get(position);
        String image_url = character.getPosterPath();
        Glide.with(context).load(image_url)
                .into(holder.characterImage);
        holder.characterTitle.setText(character.getName());
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        holder.itemView.startAnimation(animation);


        holder.characterLayout.setOnClickListener(v -> DetailsActivity.start(context, characters.get(holder.getAdapterPosition())));

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter(filterList, this);
        }
        return filter;
    }

    public void addItem(List<Characters> characters) {
        this.characters.addAll(characters);
        notifyDataSetChanged();
    }

    class CharactersViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout characterLayout;
        TextView characterTitle;
        ImageView characterImage;
//        ProgressBar progressBar;


        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            characterLayout = itemView.findViewById(R.id.search_layout);
            characterTitle = itemView.findViewById(R.id.nameTxt);
            characterImage = itemView.findViewById(R.id.searchImage);
//            progressBar = itemView.findViewById(R.id.progress);
        }
    }
}

