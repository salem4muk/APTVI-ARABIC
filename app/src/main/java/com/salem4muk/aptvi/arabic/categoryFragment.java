package com.salem4muk.aptvi.arabic;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.salem4muk.aptvi.arabic.Common.Common;
import com.salem4muk.aptvi.arabic.Interface.ItemClickListener;
import com.salem4muk.aptvi.arabic.ViewHolder.CategoryViewHolder;
import com.salem4muk.aptvi.arabic.model.Channel_Category;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class categoryFragment extends Fragment {

    // Firebase
    FirebaseDatabase database;
    DatabaseReference channel_category;

    //FirebaseUI Adapter
    FirebaseRecyclerOptions<Channel_Category> options;
    FirebaseRecyclerAdapter<Channel_Category, CategoryViewHolder> adapter;


    //view
    RecyclerView recyclerView;

    private static categoryFragment INSTANCE = null;
    private InterstitialAd mInterstitialAd;


    public categoryFragment() {

        database = FirebaseDatabase.getInstance();
        channel_category = database.getReference(Common.STR_CHANNER_CATEGORY);

        options = new FirebaseRecyclerOptions.Builder<Channel_Category>()
                .setQuery(channel_category, Channel_Category.class)
                .build();


        adapter = new FirebaseRecyclerAdapter<Channel_Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CategoryViewHolder holder, final int i, @NonNull final Channel_Category model) {

                Picasso.with(getActivity())
                        .load(model.getLink_image())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.background_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                //Try again online if cache failed
                                Picasso.with(getActivity())
                                        .load(model.getLink_image())
                                        .error(R.drawable.ic_landscape_black_24dp)
                                        .into(holder.background_image, new Callback() {
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

                holder.category_name.setText(model.getName());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {

                        // Code late for detail category


                        Common.CATEGORY_ID_SELECTED = adapter.getRef(position).getKey();
                        Common.CATEGORY_SELECTED = model.getName();
                        Intent intent = new Intent(getActivity(),ListChannel.class);
                        Common.select_category = model;
                        startActivity(intent);

                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        }
                    }
                });


            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_category, parent, false);


                return new CategoryViewHolder(itemView);
            }
        };


    }


    public static categoryFragment getInstance() {

        if (INSTANCE == null)
            INSTANCE = new categoryFragment();
        return INSTANCE;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        MobileAds.initialize(getActivity(),
                "ca-app-pub-8681464517410384~1722927745");
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-8681464517410384/2895961500");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        recyclerView = view.findViewById(R.id.recycler_category);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        setCategory();


        return view;

    }

    private void setCategory() {

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
}
