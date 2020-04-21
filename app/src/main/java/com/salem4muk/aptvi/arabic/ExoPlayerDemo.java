package com.salem4muk.aptvi.arabic;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.salem4muk.aptvi.arabic.Common.Common;


public class ExoPlayerDemo extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    String url_video ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player_demo);

        url_video = Common.select_channel.getChannelUrl();

        hideactionbar();
        setfullscreen();

        iniExoPlayer();





    }

    private void hideactionbar() {
      //  getSupportActionBar().hide();
    }

    private void setfullscreen() {

       // requestWindowFeature(Window.FEATURE_NO_TITLE);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    private void iniExoPlayer() {

        playerView = findViewById(R.id.exo_play);

        //simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
        playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory datasource = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this,url_video));

        MediaSource videosource = new ExtractorMediaSource.Factory(datasource)
                .createMediaSource(Uri.parse(url_video));
        simpleExoPlayer.prepare(videosource);

        simpleExoPlayer.setPlayWhenReady(true);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}
