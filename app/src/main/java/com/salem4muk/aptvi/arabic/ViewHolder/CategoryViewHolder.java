package com.salem4muk.aptvi.arabic.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.salem4muk.aptvi.arabic.Interface.ItemClickListener;
import com.salem4muk.aptvi.arabic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView category_name;
    public ImageView background_image;

    ItemClickListener itemClickListener;



    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        background_image = itemView.findViewById(R.id.background_image);
        category_name = itemView.findViewById(R.id.category_name);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition());

    }
}
