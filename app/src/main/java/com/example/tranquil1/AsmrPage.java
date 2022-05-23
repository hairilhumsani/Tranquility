package com.example.tranquil1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;

public class AsmrPage extends AppCompatActivity {

    Button hiPlay, icPlay, bPlay, kbPlay, sPlay, pPlay;
    Button btn;
    SharedPreference sharedPreference = new SharedPreference(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asmr_page);

        hiPlay = findViewById(R.id.hlPlay);
        icPlay = findViewById(R.id.icPlay);
        bPlay = findViewById(R.id.jofPlay);
        kbPlay = findViewById(R.id.kbPlay);
        sPlay = findViewById(R.id.swPlay);
        pPlay = findViewById(R.id.pPlay);


        Button b = findViewById(R.id.popASMR);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AsmrPage.this, PopASMR.class));

            }
        });
    }

    public void startPlaying(View v) {

        hiPlay.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.playgreen));
        icPlay.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.playgreen));
        bPlay.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.playgreen));
        kbPlay.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.playgreen));
        sPlay.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.playgreen));
        pPlay.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.playgreen));

        int raw = 0;

        switch (v.getId()) {
            case R.id.hlPlay:
                sharedPreference.setIntegerPreference("btnID", R.id.hlPlay);
                raw = R.raw.highlighter;
                break;
            case R.id.icPlay:
                sharedPreference.setIntegerPreference("btnID", R.id.icPlay);
                raw = R.raw.icechewing;
                break;
            case R.id.jofPlay:
                sharedPreference.setIntegerPreference("btnID", R.id.jofPlay);
                raw = R.raw.beads;
                break;
            case R.id.kbPlay:
                sharedPreference.setIntegerPreference("btnID", R.id.kbPlay);
                raw = R.raw.keyboard;
                break;
            case R.id.swPlay:
                sharedPreference.setIntegerPreference("btnID", R.id.swPlay);
                raw = R.raw.sweet;
                break;
            case R.id.pPlay:
                sharedPreference.setIntegerPreference("btnID", R.id.pPlay);
                raw = R.raw.plastic;
                break;
        }
        btn = findViewById(sharedPreference.getIntegerPreference("btnID",0));

        Intent serviceIntent = new Intent(this, AudioService.class);
        serviceIntent.putExtra("rawId", raw);
        this.startService(serviceIntent);
    }

    public void stopPlaying(View v)
    { Intent serviceIntent = new Intent(this, AudioService.class);
        this.stopService(serviceIntent);
    }

}



