package com.avssolutiion.ff4xdimondunilimted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.avssolutiion.ff4xdimondunilimted.databinding.ActivityMagicDekhoBinding;

public class MagicDekhoActivity extends AppCompatActivity implements MaxAdListener {

    ActivityMagicDekhoBinding binding;
    int amount;


    //applovin ads
    private MaxInterstitialAd interstitialAd,interstitialAd2,interstitialAd3,interstitialAd4;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_dekho);
        binding = ActivityMagicDekhoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        interstitialAd = new MaxInterstitialAd(getString(R.string.inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();

//        interstitialAd2 = new MaxInterstitialAd(getString(R.string.inter),this);
//        interstitialAd2.setListener(this);
//        interstitialAd2.loadAd();

        loadnetiveAd();

        // fetch coins
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyWallet",MODE_PRIVATE);
        amount = sharedPreferences1.getInt("coins",0);
        Log.d("coins", String.valueOf(amount));
       // binding.balance.setText(String.valueOf("Balance : "+amount));
        // fetch coins


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MagicDekhoActivity.this,MainActivity.class));
                finish();
            }
        });

        binding.watch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                    SharedPreferences.Editor addCoin = addAMountSp.edit();
                    int addCoins = amount+5;
                    addCoin.putInt("coins",addCoins);
                    binding.lock1.setVisibility(View.VISIBLE);
                    binding.watch1.setVisibility(View.GONE);
                    binding.lock2.setVisibility(View.GONE);
                    binding.watch2.setVisibility(View.VISIBLE);
                    binding.lock3.setImageDrawable(getDrawable(R.drawable.lock));
                    binding.lock4.setImageDrawable(getDrawable(R.drawable.lock));

//                    startActivity(getIntent());
//                    overridePendingTransition(0,0);

                }else {
                    Toast.makeText(MagicDekhoActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.watch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                    SharedPreferences.Editor addCoin = addAMountSp.edit();
                    int addCoins = amount+10;
                    addCoin.putInt("coins",addCoins);

                    binding.lock1.setVisibility(View.VISIBLE);
                    binding.watch1.setVisibility(View.GONE);

                    binding.lock2.setVisibility(View.VISIBLE);
                    binding.watch2.setVisibility(View.GONE);

                    binding.lock2.setVisibility(View.GONE);
                    binding.watch3.setVisibility(View.VISIBLE);


                    binding.lock4.setImageDrawable(getDrawable(R.drawable.lock));

//                    startActivity(getIntent());
//                    overridePendingTransition(0,0);

                }else {
                    Toast.makeText(MagicDekhoActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.watch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                    SharedPreferences.Editor addCoin = addAMountSp.edit();
                    int addCoins = amount+15;
                    addCoin.putInt("coins",addCoins);


                    binding.lock1.setVisibility(View.VISIBLE);
                    binding.watch1.setVisibility(View.GONE);

                    binding.lock2.setVisibility(View.VISIBLE);
                    binding.watch2.setVisibility(View.GONE);

                    binding.lock3.setVisibility(View.VISIBLE);
                    binding.watch3.setVisibility(View.GONE);

                    binding.lock4.setVisibility(View.GONE);
                    binding.watch4.setVisibility(View.VISIBLE);

//                    startActivity(getIntent());
//                    overridePendingTransition(0,0);
                }else {
                    Toast.makeText(MagicDekhoActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.watch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                    SharedPreferences.Editor addCoin = addAMountSp.edit();
                    int addCoins = amount+20;
                    addCoin.putInt("coins",addCoins);
                    binding.lock1.setVisibility(View.VISIBLE);
                    binding.watch1.setVisibility(View.GONE);

                    binding.lock2.setVisibility(View.VISIBLE);
                    binding.watch2.setVisibility(View.GONE);

                    binding.lock3.setVisibility(View.VISIBLE);
                    binding.watch3.setVisibility(View.GONE);

                    binding.lock4.setVisibility(View.VISIBLE);
                    binding.watch4.setVisibility(View.GONE);

//                    startActivity(getIntent());
//                    overridePendingTransition(0,0);
                }else {
                    Toast.makeText(MagicDekhoActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    void loadnetiveAd(){

        FrameLayout nativeAdContainer = findViewById( R.id.native_ad_layout );
        nativeAdLoader = new MaxNativeAdLoader( getString(R.string.netive), this );
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                nativeAdContainer.setVisibility(View.VISIBLE);
                // loadingDialog.dismiss();
                // Clean up any pre-existing native ad to prevent memory leaks.
                if ( nativeAd != null )
                {
                    nativeAdLoader.destroy( nativeAd );
                }

                // Save ad for cleanup.
                nativeAd = ad;

                // Add ad view to view.
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                // nativeAdContainer.setVisibility(View.GONE);
                // Toast.makeText(MainActivity.this, "NetiveFailed", Toast.LENGTH_SHORT).show();
                // loadingDialog.dismiss();
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
                //  loadingDialog.dismiss();
            }
        } );

        nativeAdLoader.loadAd();

    }


    @Override
    public void onAdLoaded(MaxAd ad) {

    }

    @Override
    public void onAdDisplayed(MaxAd ad) {

    }

    @Override
    public void onAdHidden(MaxAd ad) {

    }

    @Override
    public void onAdClicked(MaxAd ad) {

    }

    @Override
    public void onAdLoadFailed(String adUnitId, MaxError error) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd ad, MaxError error) {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MagicDekhoActivity.this,MainActivity.class));
        finish();
    }
}