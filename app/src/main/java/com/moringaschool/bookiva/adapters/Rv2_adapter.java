package com.moringaschool.bookiva.adapters;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.moringaschool.bookiva.R;
import com.moringaschool.bookiva.model.Items;
import com.squareup.picasso.Picasso;

public class Rv2_adapter extends RecyclerView.Adapter<Rv2_adapter.myViewHolderTwo> {
    Context context;
    List<Items> ListItems;

    public Rv2_adapter(Context context, List<Items> listItems) {
        this.context = context;
        ListItems = listItems;
    }


    @NonNull
    @Override
    public myViewHolderTwo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.random_books_design, parent, false);
        return new myViewHolderTwo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolderTwo holder, int position) {
        Items items = ListItems.get(position);
//        Glide.with(context)
//                .load(items.getVolumeInfo().getImageLinks().getThumbnail())
//                .override(100,100)
//                .error(R.drawable.illustration)
//                .into(holder.imageView);
        Picasso.get().load(items.getVolumeInfo().getImageLinks().getThumbnail())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return ListItems.size();
    }

    public class myViewHolderTwo extends RecyclerView.ViewHolder{
        public ImageView imageView;

        public myViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.editorsTextTitles);
        }
    }
}
