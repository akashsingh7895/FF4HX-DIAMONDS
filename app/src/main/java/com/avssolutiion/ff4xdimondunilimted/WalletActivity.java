package com.avssolutiion.ff4xdimondunilimted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.avssolutiion.ff4xdimondunilimted.databinding.ActivityWalletBinding;

public class WalletActivity extends AppCompatActivity implements MaxAdListener {

    ActivityWalletBinding binding;
    //applovin ads
    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWalletBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        interstitialAd = new MaxInterstitialAd(getString(R.string.inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();
        loadnetiveAd();
        //applovin

        // fetch coins
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyWallet",MODE_PRIVATE);
        amount = sharedPreferences1.getInt("coins",0);
        Log.d("coins", String.valueOf(amount));
        binding.balance.setText(String.valueOf("Balance : "+amount));
        // fetch coins

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        binding.withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    startActivity(new Intent(WalletActivity.this,WithdrawalActivity.class));

                }else {
                    startActivity(new Intent(WalletActivity.this,WithdrawalActivity.class));

                }
            }
        });


        binding.exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    startActivity(new Intent(WalletActivity.this,ExchangeActivity.class));

                }else {
                    startActivity(new Intent(WalletActivity.this,ExchangeActivity.class));

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
}