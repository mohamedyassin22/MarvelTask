package com.yassin.marveltask.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yassin.marveltask.R;
import com.yassin.marveltask.activity.DetailsActivity;
import com.yassin.marveltask.model.Characters;

import java.util.List;


public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder> {


    private List<Characters> characters;
    private Context context;

    public CharactersAdapter(List<Characters> characters) {
        this.characters = characters;
    }

    @NonNull
    @Override
    public CharactersAdapter.CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.list_items, parent, false);

        return new CharactersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CharactersViewHolder holder, final int position) {


        Characters character = characters.get(position);
        String image_url = character.getPosterPath();
        Glide.with(context).load(image_url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);

                        return false;
                    }
                })
                .into(holder.characterImage);
        holder.characterTitle.setText(character.getName());


        holder.characterLayout.setOnClickListener(v -> DetailsActivity.start(context, characters.get(holder.getAdapterPosition())));

    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class CharactersViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout characterLayout;
        Button characterTitle;
        ImageView characterImage;
        ProgressBar progressBar;


        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            characterLayout = itemView.findViewById(R.id.character_layout);
            characterTitle = itemView.findViewById(R.id.title);
            characterImage = itemView.findViewById(R.id.item_character_poster);
            progressBar = itemView.findViewById(R.id.progress);
        }
    }


    public void addItem(List<Characters> characters) {
        this.characters.addAll(characters);
        notifyDataSetChanged();
    }
}

