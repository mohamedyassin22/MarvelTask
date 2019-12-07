package com.yassin.marveltask.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.yassin.marveltask.R;
import com.yassin.marveltask.model.ComicsItems;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {
    private List<ComicsItems> comics;
    private Context context;

    public DetailsAdapter(Context context, List<ComicsItems> comics) {
        this.context = context;
        this.comics = comics;

    }

    @NonNull
    @Override
    public DetailsAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.details_item, parent, false);

        return new DetailsAdapter.DetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DetailsAdapter.DetailsViewHolder holder, final int position) {


        ComicsItems comic = comics.get(position);
        String image_url = comic.getResourceURI() + ".jpg";
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
                .into(holder.detailsImage);
        holder.detailsTitle.setText(comic.getName());


//        holder.characterLayout.setOnClickListener(v -> DetailsActivity.start(context, comics.get(holder.getAdapterPosition())));

    }

    @Override
    public int getItemCount() {
        return comics.size();
    }

    public void addItem(List<ComicsItems> comics) {
        this.comics.addAll(comics);
        notifyDataSetChanged();
    }

    class DetailsViewHolder extends RecyclerView.ViewHolder {

        //        RelativeLayout characterLayout;
        TextView detailsTitle;
        ImageView detailsImage;
        ProgressBar progressBar;


        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
//            characterLayout = itemView.findViewById(R.id.detail_layout);
            detailsTitle = itemView.findViewById(R.id.title);
            detailsImage = itemView.findViewById(R.id.item_details_poster);
            progressBar = itemView.findViewById(R.id.progress);
        }
    }
}
