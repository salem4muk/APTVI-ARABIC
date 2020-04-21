package com.salem4muk.aptvi.arabic;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.salem4muk.aptvi.arabic.Common.Common;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ViewChannel extends AppCompatActivity {

    ImageView imageView;


     String url_video ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_channel);

        url_video = Common.select_channel.getImageUrl();

        imageView = findViewById(R.id.view_image);
        Picasso.with(this)
                //.load(Common.select_channel.getImageUrl())
                .load(url_video)
                .into(imageView);
    }
}
