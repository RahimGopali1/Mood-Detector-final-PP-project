package com.example.Mood_Detector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Mood_Detector.activity.LoginActivity;
import com.example.Mood_Detector.activity.SignUpActivity;
import com.example.Mood_Detector.ui.chatbotActivity;

public class DashActivity extends AppCompatActivity {
    Button btn_bot, btn_chat, btn_music, btn_video, btn_joke, btn_quote, btn_medit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        btn_bot =(Button) findViewById(R.id.buttonBot);
        btn_chat = (Button) findViewById(R.id.buttonChat);
        btn_music = (Button) findViewById(R.id.buttonMusic);
        btn_video = (Button) findViewById(R.id.buttonVideo);
        btn_joke = (Button) findViewById(R.id.buttonJoke);
        btn_quote = (Button) findViewById(R.id.buttonQuotes);
        btn_medit = (Button) findViewById(R.id.buttonMeditation);

        btn_bot.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openchatbotActivity();
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
        btn_music.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMusicActivity();
            }
        });
        btn_joke.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openJokeActivity();
            }
        });
        btn_medit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openMeditationActivity();
            }
        });

        btn_quote.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openQuotesActivity();
            }
        });

        btn_video.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openVideoActivity();
            }
        });

    }



    private void openLoginActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    private void openMusicActivity() {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }
    private void openVideoActivity() {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

    private void openQuotesActivity() {
        Intent intent = new Intent(this, QuotesActivity.class);
        startActivity(intent);
    }

    private void openMeditationActivity() {
        Intent intent = new Intent(this, MeditationActivity.class);
        startActivity(intent);
    }

    public void openJokeActivity() {
        Intent intent = new Intent(this, jokeActivity.class);
        startActivity(intent);
    }

    public void openchatbotActivity() {
        Intent intent = new Intent(this, chatbotActivity.class);
        startActivity(intent);
    }


}
