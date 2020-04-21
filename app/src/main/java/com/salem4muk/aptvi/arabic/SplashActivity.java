package com.salem4muk.aptvi.arabic;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private Timer _timer = new Timer();
    private Intent it = new Intent();
    private LinearLayout linear1;
    private LinearLayout linear2;
    private TimerTask tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Config.ENABLE_RTL_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                startActivity(intent);

            }
        },3000);

        initialize();
        initializeLogic();
    }

    private void initialize() {
        this.linear1 = (LinearLayout) findViewById(R.id.linear);
        this.linear2 = (LinearLayout) findViewById(R.id.linear3);
    }

    private void initializeLogic() {
        this.tm = new TimerTask() {
            public void run() {
                SplashActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                     //   SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                      //  SplashActivity.this.finish();
                    }
                });
            }
        };
        this._timer.schedule(this.tm, 4000);
        this.linear2.addView(new ProgressBar(this));
    }



}
