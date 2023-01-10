package com.avssolutiion.ff4xdimondunilimted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.avssolutiion.ff4xdimondunilimted.databinding.ActivityExchangeBinding;

public class ExchangeActivity extends AppCompatActivity implements MaxAdListener {

    ActivityExchangeBinding binding;
    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExchangeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        interstitialAd = new MaxInterstitialAd(getString(R.string.inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.ex1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 1);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 1);
                    startActivity(intent);
                }

            }
        });

        binding.ex2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 2);
                    startActivity(intent);

                }else {
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 2);
                    startActivity(intent);

                }


            }
        });

        binding.ex3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 3);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 3);
                    startActivity(intent);
                }



            }
        });

        binding.ex4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 4);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 4);
                    startActivity(intent);
                }



            }
        });

        binding.ex5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 5);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 5);
                    startActivity(intent);
                }



            }
        });


        binding.ex6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (interstitialAd.isReady()){
                    interstitialAd.showAd();
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 6);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(ExchangeActivity.this,Exfctivity.class);
                    intent.putExtra("intVariableName", 6);
                    startActivity(intent);
                }



            }
        });

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