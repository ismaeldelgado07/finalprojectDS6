package com.java.kingquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gameplay extends AppCompatActivity {

    public int counter;
    TextView tw_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        tw_timer = (TextView) findViewById(R.id.tw_timer);
        startCountDown(tw_timer);

    }

    public void startCountDown(View v) {
        new CountDownTimer(15000, 1000){
            public void onTick(long millisUntilFinished){
                tw_timer.setText(String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){
                tw_timer.setText("FINISH!!");
            }
        }.start();
    }


}
