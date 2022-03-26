package com.moringaschool.bookiva.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.bookiva.R;
import com.moringaschool.bookiva.model.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Rv1_adapter extends RecyclerView.Adapter<Rv1_adapter.myViewHolder>{
    private static final String TAG = Rv1_adapter.class.getSimpleName();

    Context mContext;
    List<Items> itemsList;

    private ItemClickListner mItemClickListener; // create a var with the ItemClickListner interface object type

    public Rv1_adapter(Context mContext, List<Items> itemsList, ItemClickListner mItemClickListener) {  // pass the variables param
        this.mContext = mContext;
        this.itemsList = itemsList;
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommended_books_design, parent, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
//        Items items = itemsList.get(position);

        holder.bindBook(itemsList.get(position));
        holder.itemView.setOnClickListener(view -> {
            mItemClickListener.onItemClick(itemsList.get(position));
        });


    }

    public interface ItemClickListner{ //interface used when an item in recyclerView is Clicked!
        // one method thats will serve that purpose
        void onItemClick(Items item);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    //my ViewHolder inner class
    public class myViewHolder extends RecyclerView.ViewHolder{
         ImageView bookCover;
         TextView title;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            bookCover = itemView.findViewById(R.id.bookCover);
//            title = itemView.findViewById(R.id.titles);
        }

        public void bindBook(Items mItem) {
//            title.setText(mItem.getVolumeInfo().getTitle());
            Picasso.get().load(mItem.getVolumeInfo().getImageLinks().getThumbnail()).into(bookCover);
        }
    }






}
