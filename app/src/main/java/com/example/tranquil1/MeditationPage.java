package com.example.tranquil1;

import android.content.Intent;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MeditationPage extends AppCompatActivity {

    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeLeftinMilliseconds = 600000; //10 mins
    private boolean timerRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_page);

        Button b = (Button) findViewById(R.id.popMedBut);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MeditationPage.this, PopMeditation.class));

            }
        });

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();

            }
        });
    }
    public void startStop()
    {
        if (timerRunning) {
            stopTimer();
            Toast.makeText(MeditationPage.this,
                    "Countdown has paused", Toast.LENGTH_LONG).show();
        } else {
            startTimer();
            Toast.makeText(MeditationPage.this,
                    "Countdown has started", Toast.LENGTH_LONG).show();
        }
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftinMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftinMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
            }

        }.start();

        countdownButton.setText("PAUSE");
        timerRunning=true;
    }





    public void stopTimer() {
        countDownTimer.cancel();
        countdownButton.setText("START");
        timerRunning=false;
    }

    public void updateTimer()
    {
        int minutes=(int)timeLeftinMilliseconds/60000;
        int seconds=(int)timeLeftinMilliseconds%60000/1000;

        String timeLeftText;
        timeLeftText=""+ minutes;
        timeLeftText+=":";
        if (seconds<10) timeLeftText+= "0";
        timeLeftText+= seconds;

        countdownText.setText(timeLeftText);

    }
}





