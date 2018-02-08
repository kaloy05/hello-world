package com.ict.acer.ictquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Trf2ResultActivity extends AppCompatActivity {

    Button mRetryButton;

MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trf1_result);
        mp = MediaPlayer.create(this, R.raw.pop);

        mRetryButton = (Button) findViewById(R.id.retry);

        TextView txtScore = (TextView) findViewById(R.id.textScore);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);

        // receive the score from last activity by Intent
        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        // display current score
        txtScore.setText("Your score: " + score);

        // use Shared preferences to save the best score
        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore", 0);
        if (highscore >= score)
            txtHighScore.setText("High score: " + highscore);
        else {
            txtHighScore.setText("New highscore: " + score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }

        mRetryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Trf2ResultActivity.this,Trf2QuizActivity.class));
                mp.start();
                Trf2ResultActivity.this.finish();

            }
        });
}
    public void mnu_onClick(View view) {
        mp.start();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void ctgory_onClick(View view) {
        mp.start();
        Intent intent = new Intent(this, Category.class);
        startActivity(intent);
        finish();
    }
    public void lvl_onClick(View view) {
        mp.start();
        Intent intent = new Intent(this, True.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        //Toast.makeText(getApplicationContext(),"please Select any Button!", Toast.LENGTH_SHORT).show();
    }
}
