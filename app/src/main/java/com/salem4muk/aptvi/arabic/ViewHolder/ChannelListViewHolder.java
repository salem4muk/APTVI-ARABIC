package com.salem4muk.aptvi.arabic.ViewHolder;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.salem4muk.aptvi.arabic.Interface.ItemClickListener;
import com.salem4muk.aptvi.arabic.Interface.onClickListner;
import com.salem4muk.aptvi.arabic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ChannelListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {


    ItemClickListener itemClickListener;
    onClickListner onclicklistner;

    public ImageView Channel;
    public TextView channel_name;
    public TextView channel_true;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public void setOnItemClickListener(onClickListner onclicklistner) {
        this.onclicklistner = onclicklistner;
    }
    public ChannelListViewHolder(@NonNull View itemView) {
        super(itemView);
        Channel = itemView.findViewById(R.id.background_image);
        channel_name = itemView.findViewById(R.id.channel_name);
        channel_true = itemView.findViewById(R.id.channel_true);




        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);


    }

    @Override
    public void onClick(View v) {

        onclicklistner.onItemClick(getAdapterPosition(), v);



    }


    @Override
    public boolean onLongClick(View v) {
        onclicklistner.onItemLongClick(getAdapterPosition(), v);
        return false;
    }
}
