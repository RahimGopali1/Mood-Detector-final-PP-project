package com.example.Mood_Detector;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView t1,label;
    public static final String TAG = "dash_test";
    public myviewholder(@NonNull View itemView) {
        super(itemView);
        img = (ImageView)itemView.findViewById(R.id.img1);
        t1 = (TextView)itemView.findViewById(R.id.t1);
        label=(TextView)itemView.findViewById(R.id.label_tv);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","Image Clicked");
                if (label.getText().toString().equals("MeditationActivity")){

                }

            }
        });
    }
    private void openMeditationActivity() {
//        Intent intent = new Intent(, MeditationActivity.class);
//        startActivity(intent);
    }
}
