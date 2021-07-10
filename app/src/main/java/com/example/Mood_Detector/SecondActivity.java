package com.example.Mood_Detector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Button btn_next, btn_previous;
    RadioGroup rgQuiz;
    String [][] questionList;
    int[] increment = new int[5];
    RadioButton[] qtn_answer = new RadioButton[5];
    TextView tvQuestion;
    RadioButton rbOpt1, rbOpt2, rbOpt3, rbOpt4;
    int questionNo = 0, score = 0;
    private Button btn_skip;

    public static final String TAG = "TEST";
    String [] answers = new String[5];
    int answer_index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQuestion = (TextView) findViewById(R.id.tv_activity_quiz_question);
        rgQuiz = (RadioGroup) findViewById(R.id.rg_activity_main_option);

        rbOpt1 = (RadioButton) findViewById(R.id.rb_activity_quiz_opt1);
        rbOpt2 = (RadioButton) findViewById(R.id.rb_activity_quiz_opt2);
        rbOpt3 = (RadioButton) findViewById(R.id.rb_activity_quiz_opt3);
        rbOpt4 = (RadioButton) findViewById(R.id.rb_activity_quiz_opt4);

        btn_next = (Button) findViewById(R.id.btn_activity_quiz_next);
        btn_previous = (Button) findViewById(R.id.btn_activity_quiz_previous);
        btn_skip = (Button) findViewById(R.id.skip_btn);

        SharedPreferences preference = getApplicationContext().getSharedPreferences("Test",  0);
        SharedPreferences.Editor editor = preference.edit();

        questionList = getQuestionList();
        if(savedInstanceState != null) {
            int i = savedInstanceState.getInt("quizIndex", 0);
            questionNo = i;
        }
        displayQuestion();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = rgQuiz.getCheckedRadioButtonId();
                if(i==-1) {
                    rbOpt4.setError("Select any question");
                    return;
                }
                getScore();
                if(questionNo==4) {
                    StringBuilder sb = new StringBuilder();
                    for(int j = 0; j < answers.length; j++) {
                        if(j == answers.length-1) {
                            sb.append(answers[j]);
                        }
                        else {
                            sb.append(answers[j]).append(",");
                        }

                    }

                    Log.d("TAG", "From secondActivity: "+ sb.toString());
                    editor.putString("answers", sb.toString());
                    editor.commit();
                    Intent intent = new Intent(SecondActivity.this, MainDashActivity.class);
                    //intent.putExtra("answers",answers);
                    startActivity(intent);
                    finish();
                }
                else {
                    questionNo++;
                }
                displayQuestion();
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(score !=0 || questionNo != 0) {
                    questionNo--;
                    score = score - increment[questionNo];
                }
                displayQuestion();
            }
        });
        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDashActivity();
            }
        });

    }
    public void openDashActivity() {
        Intent intent = new Intent(this, DashActivity.class);
        startActivity(intent);
    }



    public String [][] getQuestionList(){

        String[] questions = getResources().getStringArray(R.array.question);
        String[][] questionList = new String[questions.length][6];
        for(int i=0; i<questions.length; i++) {
            String[] question = questions[i].split(",");

            for(int j=0; j<6; j++) {
                questionList[i][j] = question[j];
                //first index I stores question
            }

        }
        return questionList;
    }

    public void displayQuestion() {

        tvQuestion.setText(questionList[questionNo][0]);
        rbOpt1.setText(questionList[questionNo][1]);
        rbOpt2.setText(questionList[questionNo][2]);
        rbOpt3.setText(questionList[questionNo][3]);
        rbOpt4.setText(questionList[questionNo][4]);


        if (qtn_answer[questionNo] == null){
            rbOpt1.setChecked(false);
            rbOpt2.setChecked(false);
            rbOpt3.setChecked(false);
            rbOpt4.setChecked(false);
            rbOpt4.setError(null);
            rgQuiz.clearCheck();

        }
        else {
            qtn_answer[questionNo].setChecked(true);
        };

    }

    public void getScore() {
        int answerId = rgQuiz.getCheckedRadioButtonId();
        RadioButton answer = (RadioButton)findViewById(answerId);
        qtn_answer[questionNo] = answer;
        Log.d("TAG","Selected answer is"+answer.getText().toString());
//        answers[answer_index] = answer.getText().toString();
//        answer_index++;
//        if(answer.getText().toString().equals(questionList[questionNo][5])){
//            increment[questionNo] = 1;
//            score++;
//        }
        if(answer.getText().toString().equals("Most of the time") || answer.getText().toString().equals("Nearly all the time")) {
            answers[answer_index] = questionList[questionNo][5];
            answer_index++;
        }

        else increment[questionNo] = 0;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("qtn_no", questionNo);
        outState.putInt("score", score);
        outState.putIntArray("increment", increment);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        questionNo = savedInstanceState.getInt("qtn_no");
        score = savedInstanceState.getInt("score");
        increment = savedInstanceState.getIntArray("increment");

        displayQuestion();
    }
}