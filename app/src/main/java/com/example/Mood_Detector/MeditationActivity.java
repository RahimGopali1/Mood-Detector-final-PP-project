package com.example.Mood_Detector;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;


public class MeditationActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    ImageView icanchor;
    Animation roundingalone, stopalone;
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        btnStart= findViewById(R.id.buttonStart);
        icanchor = findViewById(R.id.icanchor);
        btnStop= findViewById(R.id.buttonStop);
        timer= findViewById(R.id.timerHere);

        //creating optional animation
       btnStop.setAlpha(0);
//        btnStart.setAlpha(0);
        roundingalone= AnimationUtils.loadAnimation(this, R.anim.roundingalone);
        stopalone= AnimationUtils.loadAnimation(this, R.anim.stopalone);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.meditation);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing animation
                icanchor.startAnimation(roundingalone);
                btnStop.animate().alpha(1).translationY(-80).setDuration(300).start();
                btnStart.animate().alpha(0).setDuration(300).start();
                timer.setBase(SystemClock.elapsedRealtime());
                timer.start();
                mediaPlayer.start();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icanchor.startAnimation(stopalone);
                btnStop.animate().alpha(0).setDuration(300).start();
                btnStart.animate().alpha(1).translationX(0).setDuration(300).start();
                timer.stop();
                mediaPlayer.stop();
            }
        });
    }
}