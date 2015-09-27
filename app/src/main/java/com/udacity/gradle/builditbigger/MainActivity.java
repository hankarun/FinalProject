package com.udacity.gradle.builditbigger;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.hankarun.hankarun.mylibrary.TellAJokeActivity;

public class MainActivity extends ActionBarActivity implements AsyncListener{
    private PublisherInterstitialAd mPublisherInterstitialAd;
    private String joke = "";
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.VERSION_NAME.equals("1.0-free")) {
            mPublisherInterstitialAd = new PublisherInterstitialAd(this);
            mPublisherInterstitialAd.setAdUnitId(getString(R.string.bannerid_ad_id));

            mPublisherInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    requestNewInterstitial();
                    startJokeActivity();
                }
            });

            requestNewInterstitial();
        }
    }

    private void requestNewInterstitial() {
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mPublisherInterstitialAd.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        dialog = new CustomDialog(this);
        dialog.show();

        EndpointAsyncTask task = new EndpointAsyncTask(this);
        task.execute();
    }

    private void startJokeActivity(){
        Intent intent = new Intent(getApplicationContext(), TellAJokeActivity.class);
        intent.putExtra("joke",joke);
        startActivity(intent);
    }

    @Override
    public void onComplete(String result) {
        joke = result;
        dialog.dismiss();
        if (mPublisherInterstitialAd.isLoaded()) {
            mPublisherInterstitialAd.show();
        } else {
            startJokeActivity();
        }

    }
}
