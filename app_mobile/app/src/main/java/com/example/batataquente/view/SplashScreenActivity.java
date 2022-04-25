package com.example.batataquente.view;

import static com.example.batataquente.R.drawable.explosao;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.batataquente.R;

public class SplashScreenActivity extends AppCompatActivity {

    Animation  anmtnBatata;
    ImageView imgViewBatata;
    ImageView imgViewLgGalpao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //  Start Batata animation 1
        anmtnBatata = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.batata_descer_contrair);
        imgViewBatata = findViewById(R.id.imgViewBatata);
        imgViewBatata.startAnimation(anmtnBatata);
        anmtnBatata.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @SuppressLint("ResourceType")
            @Override
            public void onAnimationEnd(Animation animation) {
                imgViewBatata.setImageResource(explosao);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        //  Start logo Galpao 4 animation
        imgViewLgGalpao = findViewById(R.id.imgViewLgGalpao);
        ObjectAnimator anim = ObjectAnimator.ofFloat(imgViewLgGalpao, "translationY", -200f, 0f);
        anim.setDuration(1400);
        anim.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }
}