package com.example.Mood_Detector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity {
    Button btnOk, btnExit;
    EditText txtName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        btnOk = (Button)findViewById(R.id.btn_activity_name_ok);
        btnExit = (Button)findViewById(R.id.btn_activity_name_exit);
        txtName = (EditText)findViewById(R.id.pt_activity_name_name);

        SharedPreferences preference = getApplicationContext().getSharedPreferences("Test",  0);
        SharedPreferences.Editor editor = preference.edit();


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("username",txtName.getText().toString());
                editor.commit();
                Intent intent = new Intent(NameActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}