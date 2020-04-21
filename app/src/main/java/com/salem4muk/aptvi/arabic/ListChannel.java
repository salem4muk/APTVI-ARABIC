package com.salem4muk.aptvi.arabic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.salem4muk.aptvi.arabic.Common.Common;
import com.salem4muk.aptvi.arabic.Interface.ItemClickListener;
import com.salem4muk.aptvi.arabic.Interface.onClickListner;
import com.salem4muk.aptvi.arabic.Player.ExoPlayerActivity;
import com.salem4muk.aptvi.arabic.Player.ExoPlayerHlsActivity;
import com.salem4muk.aptvi.arabic.ViewHolder.ChannelListViewHolder;
import com.salem4muk.aptvi.arabic.model.Channel_Category;
import com.salem4muk.aptvi.arabic.model.Channel_List;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ListChannel extends AppCompatActivity {

    Query query;
    FirebaseRecyclerOptions<Channel_List> options;
    FirebaseRecyclerAdapter<Channel_List, ChannelListViewHolder> adapter;

    RecyclerView recyclerView;
    String namecategory;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_channel);

        namecategory = Common.select_category.getName();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(namecategory);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_forward_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

        if (Config.ENABLE_RTL_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }
       // MobileAds.initialize(this, new OnInitializationCompleteListener() {
         //   @Override
        //    public void onInitializationComplete(InitializationStatus initializationStatus) {
         //   }
      //  });
        AdView adView = (AdView) findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        MobileAds.initialize(this,
                "ca-app-pub-8681464517410384~1722927745");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8681464517410384/2895961500");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });


        recyclerView = findViewById(R.id.recycler_list_channel);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        loadChannelList();


    }

    private void loadChannelList() {

        query = FirebaseDatabase.getInstance().getReference(Common.STR_CHANELL_LIST)
                .orderByChild("categoryId").equalTo(Common.CATEGORY_ID_SELECTED);
        options = new FirebaseRecyclerOptions.Builder<Channel_List>()
                .setQuery(query, Channel_List.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Channel_List, ChannelListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final ChannelListViewHolder holder, final int i, @NonNull final Channel_List model) {

                Picasso.with(getBaseContext())
                        .load(model.getImageUrl())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.Channel, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                                Picasso.with(getBaseContext())
                                        .load(model.getImageUrl())
                                        .error(R.drawable.ic_landscape_black_24dp)
                                        //.networkPolicy(NetworkPolicy.OFFLINE)
                                        .into(holder.Channel, new Callback() {
                                            @Override
                                            public void onSuccess() {

                                            }

                                            @Override
                                            public void onError() {
                                                Log.e("ERROR_SALEM", "Couldn't fetch image");

                                            }
                                        });


                            }
                        });

                holder.channel_name.setText(model.getChannelname());
                holder.channel_true.setText(model.getChannelUrltrue());
                holder.channel_true.setVisibility(View.GONE);
                holder.setOnItemClickListener(new onClickListner() {
                    @Override
                    public void onItemClick(int position, View v) {
                        String stringOfBooleanValue = (holder.channel_true.getText().toString().trim());
                        boolean booleanOfString = Boolean.valueOf(stringOfBooleanValue);

                        if (booleanOfString == true) {

                            Intent intent = new Intent(ListChannel.this, ExoPlayerActivity.class);
                            Common.select_channel = model;
                            startActivity(intent);
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            }

                        } else if (booleanOfString == false) {

                            Intent intent1 = new Intent(ListChannel.this, ExoPlayerHlsActivity.class);
                            Common.select_channel = model;
                            startActivity(intent1);

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            }
                        }
                    }

                    @Override
                    public void onItemLongClick(int position, View v) {
/*
                        String currentChannelname = getItem(position).getChannelname();
                        String currentCategoryId = getItem(position).getCategoryId();
                        String currentChannelUrl = getItem(position).getChannelUrl();
                        String currentChannelUrltrue = getItem(position).getChannelUrltrue();
                        String currentImageUrl = getItem(position).getImageUrl();
                        ShowDeleteDataDialog(currentChannelname,currentImageUrl);
*/


                    }
                });

            }

            @NonNull
            @Override
            public ChannelListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.channel_list_item, parent, false);

                int height = parent.getMeasuredHeight() / 2;
                itemView.setMinimumHeight(height);

                return new ChannelListViewHolder(itemView);


            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    //Ctrl+O

    @Override
    public void onStart() {
        super.onStart();

        if (adapter != null)
            adapter.startListening();
    }


    @Override
    public void onStop() {

        if (adapter != null)
            adapter.stopListening();

        super.onStop();


    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
    private void ShowDeleteDataDialog( final String currentChannelname, final String currentImageUrl) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(ListChannel.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure to delete this is Channel");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Query mQuery = FirebaseDatabase.getInstance().getReference(Common.STR_CHANELL_LIST)
                        .orderByChild("channelname").equalTo(currentChannelname);
                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){

                            ds.getRef().removeValue();
                            Toast.makeText(ListChannel.this,"Channel delete successfully...",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ListChannel.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });


                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference mImageUrl = storage.getReferenceFromUrl(currentImageUrl);
                mImageUrl.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(ListChannel.this,"Image delete successfully...",Toast.LENGTH_LONG).show();



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(ListChannel.this,e.getMessage(),Toast.LENGTH_LONG).show();


                    }
                });





            }
        });


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                dialogInterface.dismiss();

            }
        });


        builder.create().show();

    }

}
