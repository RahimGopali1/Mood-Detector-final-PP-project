package com.example.Mood_Detector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Animation topAnim, bottomAnim;
    ImageView thumb, smile;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //preference
        SharedPreferences preference = getApplicationContext().getSharedPreferences("Test",  0);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        thumb =findViewById(R.id.iv_activity_main_img);
        txt =findViewById(R.id.tv_activity_main_slogan);
        smile =findViewById(R.id.image2);

        thumb.setAnimation(topAnim);
//        txt.setAnimation(bottomAnim);
        smile.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String username = preference.getString("username","user");
                if (username == "user"){
                    Intent intent = new Intent(MainActivity.this, NameActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 5000);
    }
}