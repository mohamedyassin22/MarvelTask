package com.yassin.marveltask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yassin.marveltask.R;
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
        Glide.with(context).load(image_url).into(holder.characterImage);
        holder.characterTitle.setText(character.getName());


//        holder.itemsBinding.setClickHandler(new ClickHandlers());


    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    class CharactersViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout characterLayout;
        Button characterTitle;
        ImageView characterImage;

        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            characterLayout = itemView.findViewById(R.id.character_layout);
            characterTitle = itemView.findViewById(R.id.title);
            characterImage = itemView.findViewById(R.id.item_character_poster);
        }
    }


    //ToDo :  Define my click handler
//    public class ClickHandlers {
//        public void onClickMovie(Characters characters) {
//            MovieDetails.start(context, characters);
//        }
//    }
    public void addItem(List<Characters> characters) {
        this.characters.addAll(characters);
        notifyDataSetChanged();
    }
}

