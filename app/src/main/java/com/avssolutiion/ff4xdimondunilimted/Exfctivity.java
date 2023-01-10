package com.avssolutiion.ff4xdimondunilimted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.avssolutiion.ff4xdimondunilimted.databinding.ActivityExfctivityBinding;

public class Exfctivity extends AppCompatActivity {

    ActivityExfctivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExfctivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("intVariableName", 0);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (intValue==1){
            binding.budleImg.setImageDrawable(getDrawable(R.drawable.game1));
            binding.balance.setText(String.valueOf("1000"));
        }else if (intValue==2){
            binding.budleImg.setImageDrawable(getDrawable(R.drawable.gimg2));
            binding.balance.setText(String.valueOf("1500"));
        }else if (intValue==3){
            binding.budleImg.setImageDrawable(getDrawable(R.drawable.gimg4));
            binding.balance.setText(String.valueOf("2000"));
        }else if (intValue==4){
            binding.budleImg.setImageDrawable(getDrawable(R.drawable.gimg4));
            binding.balance.setText(String.valueOf("2500"));
        }else if (intValue==5){
            binding.budleImg.setImageDrawable(getDrawable(R.drawable.gimg5));
            binding.balance.setText(String.valueOf("3000"));
        }else if (intValue==6){
            binding.budleImg.setImageDrawable(getDrawable(R.drawable.gimg6));
            binding.balance.setText(String.valueOf("5000"));
        }

    }
}