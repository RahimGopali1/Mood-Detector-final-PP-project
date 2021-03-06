package com.example.Mood_Detector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Mood_Detector.activity.LoginActivity;
import com.example.Mood_Detector.activity.SignUpActivity;
import com.example.Mood_Detector.ui.chatbotActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainDashActivity extends AppCompatActivity {

    RecyclerView rcv;
    myadapter adapter;
    FloatingActionButton actionButton;
    public static final String TAG = "dash_test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dash);

        actionButton = findViewById(R.id.bt_float);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainDashActivity.this,DashActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences preference = getApplicationContext().getSharedPreferences("Test",  0);
        String answersAll = preference.getString("answers", "");
        String[] answers = answersAll.split(",");
        //String [] answers = getIntent().getStringArrayExtra("answers");
        Log.d("TAG", "From dash"+answers[0]);
        Log.d("TAG", "All answers"+ answersAll);

        rcv = (RecyclerView)findViewById(R.id.rclview);
        adapter = new myadapter(dataqueue(answers),getApplicationContext());
        rcv.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rcv.setLayoutManager(gridLayoutManager);
    }

    public ArrayList<DataModel> dataqueue(String [] answers)

    {
        Boolean display = false;

        ArrayList<DataModel> holder= new ArrayList<>();

        DataModel ob1 = new DataModel();
        ob1.setHeader("Chat Bot");
        ob1.setImgname(R.drawable.bot);
        holder.add(ob1);

        DataModel ob2 = new DataModel();
        ob2.setHeader("Chat With Friends");
        ob2.setImgname(R.drawable.chat);
        holder.add(ob2);

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] != null) {
                if (answers[i].equals("Music")) {
                    display = true;
                    break;
                }
            }
        }
        if (display) {
            DataModel ob3 = new DataModel();
            ob3.setHeader("Music");
            ob3.setImgname(R.drawable.music);
            holder.add(ob3);
        }
        display = false;

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] != null) {
                if (answers[i].equals("Video")) {
                    display = true;
                    break;
                }
            }
        }
        if (display) {
            DataModel ob4 = new DataModel();
            ob4.setHeader("Video");
            ob4.setImgname(R.drawable.video);
            holder.add(ob4);
        }
        display = false;

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] != null) {
                if (answers[i].equals("Jokes")) {
                    display = true;
                    break;
                }
            }
        }
        if (display) {
            DataModel ob5 = new DataModel();
            ob5.setHeader("Jokes");
            ob5.setImgname(R.drawable.joke);
            holder.add(ob5);
        }
        display = false;

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] != null) {
                if (answers[i].equals("Quotes")) {
                    display = true;
                    break;
                }
            }
        }
        if (display) {
            DataModel ob6 = new DataModel();
            ob6.setHeader("Quotes");
            ob6.setImgname(R.drawable.quotes);
            holder.add(ob6);
        }
        display = false;

        for (int i = 0; i < answers.length; i++) {
            if(answers[i] != null) {
                if (answers[i].equals("Meditation")) {
                    display = true;
                    break;
                }
            }
        }
        if (display) {
            DataModel ob7 = new DataModel();
            ob7.setHeader("Meditation");
            ob7.setImgname(R.drawable.med);
            ob7.setLabel("MeditationActivity");
            holder.add(ob7);
        }
        display = false;


        return holder;
    }

    //Adapter
    public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
        ArrayList<DataModel> data;
        Context context;
        public myadapter(ArrayList<DataModel> data, Context context) {
            this.data = data;
            this.context = context;
        }

        @NonNull
        @Override
        public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.singlerow,parent,false);
            return new myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull myviewholder holder, int position) {
            final DataModel temp =data.get(position);

            holder.t1.setText(data.get(position).getHeader());
            holder.img.setImageResource(data.get(position).getImgname());
            holder.label.setText(data.get(position).getLabel());

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Intent intent = new Intent(context,);
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

            ImageView img;
            TextView t1,label;
            public static final String TAG = "dash_test";
            public myviewholder(@NonNull View itemView) {
                super(itemView);
                img = (ImageView)itemView.findViewById(R.id.img1);
                t1 = (TextView)itemView.findViewById(R.id.t1);
                label=(TextView)itemView.findViewById(R.id.label_tv);
                itemView.setClickable(true);
                itemView.setOnClickListener(this);


//                img.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(context,"The Item Clicked is: "+ getBindingAdapterPosition(),Toast.LENGTH_SHORT).show();
//                        Log.d("TAG","Image Clicked");
//                        if (label.getText().toString().equals("MeditationActivity")){
//
//                        }
//
//                    }
//                });
            }

            @Override
            public void onClick(View v) {
                final Intent intent;
                switch (getBindingAdapterPosition()) {
                    case 0:
                        intent = new Intent(context, chatbotActivity.class);
                        break;
                    case 1:
                        intent = new Intent(context, SignUpActivity.class);
                        break;
                    case 2:
                        intent = new Intent(context, MusicActivity.class);
                        break;
                    case 3:
                        intent = new Intent(context, VideoActivity.class);
                        break;
                    case 4:
                        intent = new Intent(context, jokeActivity.class);
                        break;
                    case 5:
                        intent = new Intent(context, QuotesActivity.class);
                        break;
                    case 6:
                        intent = new Intent(context, MeditationActivity.class);
                        break;
                    default:
                        intent = new Intent(context, chatbotActivity.class);
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(context,"The Item Clicked is: "+ getBindingAdapterPosition(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    //View holder
}