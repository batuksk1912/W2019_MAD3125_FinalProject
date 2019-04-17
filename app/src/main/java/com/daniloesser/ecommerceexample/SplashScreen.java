package com.daniloesser.ecommerceexample;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class SplashScreen extends AppCompatActivity {

    private static int timeout = 3000;

    ImageView splashimage;
    TextView splashtxtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashimage = findViewById(R.id.splash_imageview);
        splashtxtv = findViewById(R.id.splash_txtv);

        Animation animation = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.splash_animation);
        splashimage.startAnimation(animation);
        splashtxtv.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },timeout);
    }
}
