package com.salem4muk.aptvi.arabic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.text.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;


public class AddActivity extends AppCompatActivity {

    private Timer _timer = new Timer();

    private String a = "";
    private String b = "";

    private LinearLayout linear1;
    private ScrollView vscroll1;
    private LinearLayout linear2;
    private LinearLayout linear3;
    private LinearLayout linear4;
    private LinearLayout linear5;
    private LinearLayout linear6;
    private LinearLayout linear7;
    private LinearLayout linear8;
    private LinearLayout linear9;
    private LinearLayout linear10;
    private LinearLayout linear11;
    private LinearLayout linear12;
    private LinearLayout linear13;
    private LinearLayout linear14;
    private LinearLayout linear15;
    private LinearLayout linear16;
    private LinearLayout linear17;
    private LinearLayout linear18;
    private LinearLayout linear19;
    private LinearLayout linear20;
    private Button button1;
    private LinearLayout linear21;
    private Button button2;
    private LinearLayout linear22;
    private Button button3;
    private LinearLayout linear23;
    private Button button4;
    private LinearLayout linear24;
    private Button button5;
    private LinearLayout linear25;
    private Button button6;
    private LinearLayout linear26;
    private Button button7;
    private LinearLayout linear27;
    private Button button8;

    private Intent intent = new Intent();
    private TimerTask timer;
    private AlertDialog.Builder dialog;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("حول المطور");
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


        initialize();

    }
        private void initialize() {

            linear1 = (LinearLayout) findViewById(R.id.linear1);
            vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
            linear2 = (LinearLayout) findViewById(R.id.linear2);
            linear3 = (LinearLayout) findViewById(R.id.linear3);
            linear4 = (LinearLayout) findViewById(R.id.linear4);
            linear5 = (LinearLayout) findViewById(R.id.linear5);
            linear6 = (LinearLayout) findViewById(R.id.linear6);
            linear7 = (LinearLayout) findViewById(R.id.linear7);
            linear8 = (LinearLayout) findViewById(R.id.linear8);
            linear9 = (LinearLayout) findViewById(R.id.linear9);
            linear10 = (LinearLayout) findViewById(R.id.linear10);
            linear11 = (LinearLayout) findViewById(R.id.linear11);
            linear12 = (LinearLayout) findViewById(R.id.linear12);
            linear13 = (LinearLayout) findViewById(R.id.linear13);
            linear14 = (LinearLayout) findViewById(R.id.linear14);
            linear15 = (LinearLayout) findViewById(R.id.linear15);
            linear16 = (LinearLayout) findViewById(R.id.linear16);
            linear17 = (LinearLayout) findViewById(R.id.linear17);
            linear18 = (LinearLayout) findViewById(R.id.linear18);
            linear19 = (LinearLayout) findViewById(R.id.linear19);
            linear20 = (LinearLayout) findViewById(R.id.linear20);
            button1 = (Button) findViewById(R.id.button1);
            linear21 = (LinearLayout) findViewById(R.id.linear21);
            button2 = (Button) findViewById(R.id.button2);
            linear22 = (LinearLayout) findViewById(R.id.linear22);
            button3 = (Button) findViewById(R.id.button3);
            linear23 = (LinearLayout) findViewById(R.id.linear23);
            button4 = (Button) findViewById(R.id.button4);
            linear24 = (LinearLayout) findViewById(R.id.linear24);
            button5 = (Button) findViewById(R.id.button5);
            linear25 = (LinearLayout) findViewById(R.id.linear25);
            button6 = (Button) findViewById(R.id.button6);
            linear26 = (LinearLayout) findViewById(R.id.linear26);
            button7 = (Button) findViewById(R.id.button7);
            linear27 = (LinearLayout) findViewById(R.id.linear27);
            button8 = (Button) findViewById(R.id.button8);
            dialog = new AlertDialog.Builder(this);

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.facebook.com/yousefaloon2/"));
                    startActivity(intent);
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.youtube.com/channel/UCsf1lBc89AaluUC5OEBl8oQ"));
                    startActivity(intent);
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.instagram.com/yousefaloon/"));
                    startActivity(intent);
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.sdrama.de/?m=1"));
                    startActivity(intent);
                }
            });

            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://t.me/joinchat/AAAAAEyrUGwDrYz4Gl1gZw"));
                    startActivity(intent);
                }
            });

            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    a = "iptv arabic هوا برنامج يقدم لك العديد من القنوات العربية المشفرة حمل الطبيق الأن وجربه الأن مجانآ https://play.google.com/store/apps/details?id=com.iptv.arabic.yousef.aloon";
                    b = "iptv arabic هوا برنامج يقدم لك العديد من القنوات العربية المشفرة حمل الطبيق الأن وجربه الأن مجانآ https://play.google.com/store/apps/details?id=com.iptv.arabic.yousef.aloon";
                    Intent i = new Intent(android.content.Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_SUBJECT, a); i.putExtra(android.content.Intent.EXTRA_TEXT, b); startActivity(Intent.createChooser(i,"Share using"));
                }
            });

            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.iptv.arabic.yousef.aloon"));
                    startActivity(intent);
                }
            });

            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=SUIT.TV"));
                    startActivity(intent);
                }
            });
        }


        @Override
        protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
            super.onActivityResult(_requestCode, _resultCode, _data);

            switch (_requestCode) {

                default:
                    break;
            }
        }

        @Deprecated
        public void showMessage(String _s) {
            Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
        }

        @Deprecated
        public int getLocationX(View _v) {
            int _location[] = new int[2];
            _v.getLocationInWindow(_location);
            return _location[0];
        }

        @Deprecated
        public int getLocationY(View _v) {
            int _location[] = new int[2];
            _v.getLocationInWindow(_location);
            return _location[1];
        }

        @Deprecated
        public int getRandom(int _min, int _max) {
            Random random = new Random();
            return random.nextInt(_max - _min + 1) + _min;
        }

        @Deprecated
        public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
            ArrayList<Double> _result = new ArrayList<Double>();
            SparseBooleanArray _arr = _list.getCheckedItemPositions();
            for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
                if (_arr.valueAt(_iIdx))
                    _result.add((double)_arr.keyAt(_iIdx));
            }
            return _result;
        }

        @Deprecated
        public float getDip(int _input){
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
        }

        @Deprecated
        public int getDisplayWidthPixels(){
            return getResources().getDisplayMetrics().widthPixels;
        }

        @Deprecated
        public int getDisplayHeightPixels(){
            return getResources().getDisplayMetrics().heightPixels;
        }

    }
