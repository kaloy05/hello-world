package com.ict.acer.ictquiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Trf2QuizActivity extends AppCompatActivity {
    private TextView mScoreView, mQuestion;
    private ImageView mImageView;
    private Button mTrueButton, mFalseButton;

    private boolean mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    Trf2QuizActivity.CounterClass timer = new Trf2QuizActivity.CounterClass(15000, 1000);

    TextView times,life;
    int lives = 3;
    Random r;

    MediaPlayer mpc;
    MediaPlayer mpw;
    MediaPlayer mpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_trf1_quiz);
        mpc = MediaPlayer.create(this, R.raw.correct);
        mpw = MediaPlayer.create(this, R.raw.wrong);
        mpt = MediaPlayer.create(this, R.raw.timeup);


        r = new Random();

        mScoreView = (TextView) findViewById(R.id.points);
        mImageView = (ImageView) findViewById(R.id.laptop1);
        mQuestion = (TextView) findViewById(R.id.question);
        mTrueButton = (Button) findViewById(R.id.trueButton);
        mFalseButton = (Button) findViewById(R.id.ButtonFalse);

        times = (TextView) findViewById(R.id.timer);
        life = (TextView) findViewById(R.id.save);

        updateQuestion();

        times.setText("");
        life.setText(""+lives);
        timer.start();


        //logic for true button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer == true) {
                    mScore++; //this update the score int variable
                    updateScore(mScore);// this convert the int variable to a Strig and adds it to  mScoreView
                    timer.cancel();

                    //perform this check before  you update the question
                    if (mQuestionNumber == TQuizbook2.question.length) {

                        Intent i = new Intent(Trf2QuizActivity.this, Trf2ResultActivity.class);
                        i.putExtra("score", mScore); // pass the current score to the second screen
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        Trf2QuizActivity.this.finish();
                        startActivity(i);

                    } else {
                        timer.cancel();
                        mpc.start();
                        Toast.makeText(Trf2QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                        timer.start();
                    }
                }
                //if the user's answer is wrong
                else {
                    lives--;
                    mpw.start();
                    Toast.makeText(Trf2QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                   //lives--;
                    if (mQuestionNumber == TQuizbook2.question.length) {

                        Intent i = new Intent(Trf2QuizActivity.this, Trf2ResultActivity.class);
                        i.putExtra("score", mScore); // pass the current score to the second screen
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        Trf2QuizActivity.this.finish();
                        timer.cancel();
                        startActivity(i);
                    }else {
                        life.setText(""+lives);
                        updateQuestion();
                        // life.setText("Lives :" +lives);
                        timer.start();
                    }
                    if (lives==0){
                        Toast.makeText(Trf2QuizActivity.this, "You have no life left!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Trf2QuizActivity.this, Trf2ResultActivity.class);
                        intent.putExtra("score", mScore); // pass the current score to the second screen
                        startActivity(intent);
                        timer.cancel();
                        Trf2QuizActivity.this.finish();
                    }
                }
            }
        });

        //logic for False button
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswer == false) {
                    mScore++; //this update the score int variable
                    updateScore(mScore);// this convert the int variable to a Strig and adds it to  mScoreView
                    timer.cancel();

                    //perform this check before  you update the question
                    if (mQuestionNumber == TQuizbook2.question.length) {

                        Intent i = new Intent(Trf2QuizActivity.this, Trf2ResultActivity.class);
                        i.putExtra("score", mScore); // pass the current score to the second screen
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        Trf2QuizActivity.this.finish();
                        startActivity(i);
                        updateQuestion();
                    } else {
                        timer.cancel();
                        mpc.start();
                        Toast.makeText(Trf2QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                        updateQuestion();
                        timer.start();
                    }

                }
                //if the user's answer is wrong
                else {
                    lives--;
                    mpw.start();
                    Toast.makeText(Trf2QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    if (mQuestionNumber == TQuizbook2.question.length) {
                        Intent i = new Intent(Trf2QuizActivity.this, Trf2ResultActivity.class);
                        i.putExtra("score", mScore); // pass the current score to the second screen
                        Bundle bundle = new Bundle();
                        bundle.putInt("finalScore", mScore);
                        i.putExtras(bundle);
                        timer.cancel();
                        startActivity(i);
                        Trf2QuizActivity.this.finish();
                    } else {
                        life.setText("" +lives);
                        updateQuestion();
                        timer.start();
                    }
                    if (lives==0){
                        Toast.makeText(Trf2QuizActivity.this, "You have no life left!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Trf2QuizActivity.this, Trf2ResultActivity.class);
                        intent.putExtra("score", mScore); // pass the current score to the second screen
                        startActivity(intent);
                        timer.cancel();
                        Trf2QuizActivity.this.finish();
                    }
                }
            }
        });
    }

    private void updateQuestion() {

        mImageView.setImageResource(TQuizbook2.images[mQuestionNumber]);
        mQuestion.setText(TQuizbook2.question[mQuestionNumber]);
        mAnswer = TQuizbook2.answers[mQuestionNumber];
        mQuestionNumber++;
    }

    public void updateScore(int points) {
        mScoreView.setText("" + mScore);
    }


    ///


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            times.setText("00");
            Intent intent = new Intent(Trf2QuizActivity.this, Trf2ResultActivity.class);
            intent.putExtra("score", mScore); // pass the current score to the second screen
            startActivity(intent);
            Trf2QuizActivity.this.finish();
            timer.cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d",
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            times.setText(hms);
        }

    }
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        timer.cancel();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Trf2QuizActivity.this);
        alertDialogBuilder
                .setIcon(R.drawable.icon)
                .setTitle("TRUE or FALSE")
                .setMessage("Are you sure you want to quit the game ? ")
                .setCancelable(false)

                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                startActivity(new Intent(getApplicationContext(), True.class));
                                Toast.makeText(getApplicationContext(),"YOU QUIT THE GAME!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                .setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int Which) {
                                dialog.dismiss();
                                timer.start();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}


