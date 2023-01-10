package com.avssolutiion.ff4xdimondunilimted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.avssolutiion.ff4xdimondunilimted.databinding.ActivityMagicCardBinding;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.util.Objects;
import java.util.Random;

public class MagicCardActivity extends AppCompatActivity implements MaxAdListener {

    ActivityMagicCardBinding binding;
    Dialog dialog;
    Random random;
    int randomNumber,randomNumber2,randomNumber3,randomNumber4;

    int amount;
    int addCoins;
    private MaxInterstitialAd interstitialAd;
    private MaxNativeAdLoader nativeAdLoader;
    private MaxAd nativeAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMagicCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog = new Dialog(MagicCardActivity.this);

        interstitialAd = new MaxInterstitialAd(getString(R.string.inter),this);
        interstitialAd.setListener(this);
        interstitialAd.loadAd();

       // Get/Set the time in milliseconds (ms) after the view is auto flip back to original front side
        int autoflipBackTimeInMilliseconds = binding.cardflip1.getAutoFlipBackTime();
        binding.cardflip1.setAutoFlipBackTime(100000);


        random = new Random();
        randomNumber = random.nextInt(25);
        randomNumber2 = random.nextInt(25);
        randomNumber3 = random.nextInt(25);
        randomNumber4 = random.nextInt(25);

        // fetch coins
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyWallet",MODE_PRIVATE);
        amount = sharedPreferences1.getInt("coins",0);
        // binding.balance.setText(String.valueOf("Balance : "+amount));
        // fetch coins

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MagicCardActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        binding.cardflip1.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

             // binding.backSide.setImageDrawable(getDrawable(R.drawable.game1));

                if (binding.cardflip1.isBackSide()){
                    binding.cardflip1.setFlipEnabled(false);
                    boolean flipStatus = binding.cardflip1.isFlipEnabled();
                    binding.text2.setText(String.valueOf(randomNumber));
                    dialog = new Dialog(MagicCardActivity.this);
                    dialog.setContentView(R.layout.card_layout);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
                    dialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    TextView balance = dialog.findViewById(R.id.textam);
                    balance.setText(String.valueOf(randomNumber));
                    ImageView imageView = dialog.findViewById(R.id.dismiss);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    CardView addToWallet = dialog.findViewById(R.id.wallet);
                    CardView thank = dialog.findViewById(R.id.thank);
                    addToWallet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (interstitialAd.isReady()){
                                interstitialAd.showAd();
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber;
                                addCoin.putInt("coins",addCoins);
                                addCoin.commit();
                                dialog.dismiss();
                            }else {
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber;
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
                }



            }
        });

        binding.cardflip2.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                // binding.backSide.setImageDrawable(getDrawable(R.drawable.game1));

                if (binding.cardflip2.isBackSide()){
                    binding.cardflip2.setFlipEnabled(false);
                    boolean flipStatus = binding.cardflip1.isFlipEnabled();
                    binding.text3.setText(String.valueOf(randomNumber2));
                    dialog = new Dialog(MagicCardActivity.this);
                    dialog.setContentView(R.layout.card_layout);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
                    dialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    TextView balance = dialog.findViewById(R.id.textam);
                    balance.setText(String.valueOf(randomNumber2));
                    ImageView imageView = dialog.findViewById(R.id.dismiss);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    CardView addToWallet = dialog.findViewById(R.id.wallet);
                    CardView thank = dialog.findViewById(R.id.thank);
                    addToWallet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (interstitialAd.isReady()){
                                interstitialAd.showAd();
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber2;
                                addCoin.putInt("coins",addCoins);
                                addCoin.commit();
                                dialog.dismiss();
                            }else {
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber2;
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
                }



            }
        });

        binding.cardflip3.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                // binding.backSide.setImageDrawable(getDrawable(R.drawable.game1));

                if (binding.cardflip3.isBackSide()){
                    binding.cardflip3.setFlipEnabled(false);
                    boolean flipStatus = binding.cardflip1.isFlipEnabled();
                    binding.text4.setText(String.valueOf(randomNumber3));
                    dialog = new Dialog(MagicCardActivity.this);
                    dialog.setContentView(R.layout.card_layout);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
                    dialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    TextView balance = dialog.findViewById(R.id.textam);
                    balance.setText(String.valueOf(randomNumber3));
                    ImageView imageView = dialog.findViewById(R.id.dismiss);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    CardView addToWallet = dialog.findViewById(R.id.wallet);
                    CardView thank = dialog.findViewById(R.id.thank);
                    addToWallet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (interstitialAd.isReady()){
                                interstitialAd.showAd();
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber3;
                                addCoin.putInt("coins",addCoins);
                                addCoin.commit();
                                dialog.dismiss();
                            }else {
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber3;
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
                }



            }
        });

        binding.cardflip4.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                // binding.backSide.setImageDrawable(getDrawable(R.drawable.game1));

                if (binding.cardflip4.isBackSide()){
                    binding.cardflip4.setFlipEnabled(false);
                    boolean flipStatus = binding.cardflip1.isFlipEnabled();
                    binding.text5.setText(String.valueOf(randomNumber4));
                    dialog = new Dialog(MagicCardActivity.this);
                    dialog.setContentView(R.layout.card_layout);
                    dialog.setCancelable(false);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
                    dialog.getWindow().setLayout(600, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    TextView balance = dialog.findViewById(R.id.textam);
                    balance.setText(String.valueOf(randomNumber4));
                    ImageView imageView = dialog.findViewById(R.id.dismiss);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                    CardView addToWallet = dialog.findViewById(R.id.wallet);
                    CardView thank = dialog.findViewById(R.id.thank);
                    addToWallet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (interstitialAd.isReady()){
                                interstitialAd.showAd();
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber4;
                                addCoin.putInt("coins",addCoins);
                                addCoin.commit();
                                dialog.dismiss();
                            }else {
                                SharedPreferences addAMountSp = getSharedPreferences("MyWallet",MODE_PRIVATE);
                                SharedPreferences.Editor addCoin = addAMountSp.edit();
                                addCoins = amount+randomNumber4;
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MagicCardActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}