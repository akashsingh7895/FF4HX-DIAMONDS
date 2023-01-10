package com.avssolutiion.ff4xdimondunilimted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.avssolutiion.ff4xdimondunilimted.databinding.ActivityMagicWheelBinding;

import java.util.Objects;
import java.util.Random;

public class MagicWheelActivity extends AppCompatActivity implements MaxAdListener {

    ActivityMagicWheelBinding binding;

    Dialog dialog;
    private static final int[]  sectors = {10,03,20,100,5,8,500,70};
    private static final int[] sectorsDegrees = new int[sectors.length];
    private static final Random random = new Random();

    private int degree = 0;
    private boolean isSpinning = false;

    int wonAmount;
    int amount;
    int addCoins;
    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMagicWheelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        interstitialAd = new MaxInterstitialAd(getString(R.string.inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();
        loadnetiveAd();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MagicWheelActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.spitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSpinning){
                    spin();
                    isSpinning = true;
                    // spinCounterPlus =  spinCounter++;
                }
            }
        });
        getDegreesForSectors();

        // fetch coins
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyWallet",MODE_PRIVATE);
        amount = sharedPreferences1.getInt("coins",0);
       // binding.balance.setText(String.valueOf("Balance : "+amount));
        // fetch coins


    }
    private void spin(){
        degree = random.nextInt(sectors.length-1);
        RotateAnimation rotateAnimation = new RotateAnimation(0,(360*sectors.length)+sectorsDegrees[degree],
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);

        rotateAnimation.setDuration(1600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                wonAmount= sectors[sectors.length-(degree+1)];
              //  Toast.makeText(MagicWheelActivity.this, "you won" + wonAmount, Toast.LENGTH_SHORT).show();
                dialog = new Dialog(MagicWheelActivity.this);
                dialog.setContentView(R.layout.card_layout);
                dialog.setCancelable(false);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
                dialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
                TextView balance = dialog.findViewById(R.id.textam);
                balance.setText(String.valueOf(wonAmount));
                CardView addToWallet = dialog.findViewById(R.id.wallet);
                CardView thank = dialog.findViewById(R.id.thank);
                ImageView imageView = dialog.findViewById(R.id.dismiss);
                addToWallet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (interstitialAd.isReady()){
                            interstitialAd.showAd();
                            SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                            SharedPreferences.Editor addCoin = addAMountSp.edit();
                            addCoins = amount+wonAmount;
                            addCoin.putInt("coins",addCoins);
                            addCoin.commit();
                           dialog.dismiss();
                        }else {
                            SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                            SharedPreferences.Editor addCoin = addAMountSp.edit();
                            addCoins = amount+wonAmount;
                            addCoin.putInt("coins",addCoins);
                            addCoin.commit();
                            dialog.dismiss();

                        }
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });




                isSpinning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.spinWheel.startAnimation(rotateAnimation);


    }
    public void getDegreesForSectors(){
        int sectorsDegree = 360/sectors.length;

        for (int i = 0;i<sectors.length;i++){
            sectorsDegrees[i] =(i+1)*sectorsDegree;
        }
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
        Intent intent = new Intent(MagicWheelActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}