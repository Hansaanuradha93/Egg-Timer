package com.example.hansaanuradhawickramanayake.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.sax.TextElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerTextView;
    boolean countdownIsActive = false;
    Button timerButton;
    CountDownTimer countDownTimer;

    public void resetTimer(){

        timerTextView.setText("00:30");
        timerSeekBar.setProgress(30);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        timerButton.setText("Go!");
        countdownIsActive = false;
    }

    public void setTimer(View view){

        if (countdownIsActive){
            resetTimer();

        } else {
            countdownIsActive = true;
            timerSeekBar.setEnabled(false);
            timerButton.setText("Stop!");


            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.water);
                    mediaPlayer.start();
                    resetTimer();

                }
            }.start();
        }

    }

    public void updateTimer(int secondsLeft){

        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String minutesString = Integer.toString(minutes);
        String secondsString = Integer.toString(seconds);

        if (seconds <= 9){
            secondsString = "0"+ seconds;
        }


        timerTextView.setText(minutesString + ":" + secondsString);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerTextView = findViewById(R.id.timerTextView);
        timerButton = findViewById(R.id.timerButton);

        timerSeekBar.setMax(300);
        timerSeekBar.setProgress(15);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
