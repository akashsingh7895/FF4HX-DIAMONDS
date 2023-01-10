package com.avssolutiion.ff4xdimondunilimted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
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
import com.avssolutiion.ff4xdimondunilimted.databinding.ActivityMagicFadeBinding;

import java.util.Random;

public class MagicFadeActivity extends AppCompatActivity implements MaxAdListener {

    ActivityMagicFadeBinding binding;
    //applovin ads
    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    private static final int[]  sectors = {R.drawable.game1,R.drawable.gimg2,R.drawable.gimg3,R.drawable.gimg4,R.drawable.gimg5,R.drawable.gimg6,R.drawable.gimg7};
    private static final int[] sectorsDegrees = new int[sectors.length];
    private static final Random random = new Random();

    private int degree = 0;
    private boolean isSpinning = false;

    int wonAmount;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMagicFadeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        interstitialAd = new MaxInterstitialAd(getString(R.string.inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();
        loadnetiveAd();

        dialog = new Dialog(MagicFadeActivity.this);
        binding.spinWheel.setOnClickListener(new View.OnClickListener() {
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


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MagicFadeActivity.this,MainActivity.class));
                finish();
            }
        });


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
              //  Toast.makeText(MagicFadeActivity.this, "you won" + wonAmount, Toast.LENGTH_SHORT).show();


                dialog = new Dialog(MagicFadeActivity.this);
                dialog.setContentView(R.layout.fade_spin_diloag);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
                dialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
               // TextView amount = dialog.findViewById(R.id.textam);
                ImageView imageView = dialog.findViewById(R.id.bimg);
                imageView.setImageDrawable(getDrawable(wonAmount));

                CardView claim = dialog.findViewById(R.id.wallet);
                CardView thank = dialog.findViewById(R.id.thank);

                claim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (interstitialAd.isReady()){
                            interstitialAd.showAd();
                            startActivity(new Intent(MagicFadeActivity.this,ExchangeActivity.class));
                            dialog.dismiss();
                        }else {
                            startActivity(new Intent(MagicFadeActivity.this,ExchangeActivity.class));
                            dialog.dismiss();
                        }
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
        startActivity(new Intent(MagicFadeActivity.this,MainActivity.class));
        finish();    }
}