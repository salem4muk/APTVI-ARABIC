package com.salem4muk.aptvi.arabic;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest.Builder;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AlertDialog.Builder dialogBuilder;
    ConsentForm form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Config.ENABLE_RTL_MODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }

        ConsentInformation consentInformation = ConsentInformation.getInstance(this);
        String[] publisherIds = {"pub-9009926091861329"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });
        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("https://sites.google.com/view/suit-tv");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
         form = new ConsentForm.Builder(this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                        form.show();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();
        form.load();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdView adView = (AdView) findViewById(R.id.adView);
        adView.loadAd(new Builder().build());
        InterstitialAd mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8681464517410384/8096764409");
        mInterstitialAd.loadAd(new Builder().build());

        if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == -1) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 1000);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                    dialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                }
                else{

                    showDialog();
                }
            }



            // super.onBackPressed();


        }
        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {


            case R.id.action_add:

                startActivity(new Intent(HomeActivity.this, Add_ChannelActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("مرحبا كيف حالك ؟ اود ان اشاركك هذا التطبيق حيث سوف يقوم بتوفير كافة القنوات المباشرة متوفر الان علي جوجل بلاي حملة الان !\n https://play.google.com/store/apps/details?id=");
            stringBuilder2.append(getPackageName());
            stringBuilder2.append(" \n\n");
            String stringBuilder3 = stringBuilder2.toString();
            intent.putExtra("android.intent.extra.SUBJECT", "APTVI ARABIC");
            intent.putExtra("android.intent.extra.TEXT", stringBuilder3);
            startActivity(Intent.createChooser(intent, "شارك بواسطة"));

        } else if (id == R.id.nav_rate) {

            String packageName = getPackageName();
            StringBuilder stringBuilder;
            try {
                stringBuilder = new StringBuilder();
                stringBuilder.append("market://details?id=");
                stringBuilder.append(packageName);
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
            } catch (ActivityNotFoundException unused) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("https://play.google.com/store/apps/details?id=");
                stringBuilder.append(packageName);
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringBuilder.toString())));
            }

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.EMAIL", new String[]{"yousefaloon22@gmail.com"});
            intent.putExtra("android.intent.extra.SUBJECT", "تحسين تطبيق APTVI ARABIC");
            intent.setType("text/plain");
            ResolveInfo resolveInfo = null;
            for (ResolveInfo resolveInfo2 : getPackageManager().queryIntentActivities(intent, 0)) {
                if (resolveInfo2.activityInfo.packageName.endsWith(".gm") || resolveInfo2.activityInfo.name.toLowerCase().contains("gmail")) {
                    resolveInfo = resolveInfo2;
                }
            }
            if (resolveInfo != null) {
                intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            }
            startActivity(intent);

        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(HomeActivity.this, AddActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_close) {
            showDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showDialog() {
        // MyDialog myDialog = new MyDialog();
        // myDialog.show(getSupportFragmentManager(),"my_dialog");


        // ...Irrelevant code for customizing the buttons and title
         dialogBuilder = new AlertDialog.Builder(this);
        // ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialo_exit_app, null);
        dialogBuilder.setView(dialogView);

        Button share = dialogView.findViewById(R.id.dim1);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("مرحبا كيف حالك ؟ اود ان اشاركك هذا التطبيق حيث سوف يقوم بتوفير كافة القنوات المباشرة متوفر الان علي جوجل بلاي حملة الان !\n https://play.google.com/store/apps/details?id=");
                stringBuilder2.append(getPackageName());
                stringBuilder2.append(" \n\n");
                String stringBuilder3 = stringBuilder2.toString();
                intent.putExtra("android.intent.extra.SUBJECT", "APTVI ARABIC");
                intent.putExtra("android.intent.extra.TEXT", stringBuilder3);
                startActivity(Intent.createChooser(intent, "شارك بواسطة"));

            }
        });

        Button dim = dialogView.findViewById(R.id.close);
        dim.setEnabled(true);
        dim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeActivity.this.finishAffinity();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        dialogBuilder.show();



    }


}
