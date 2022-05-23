package com.example.tranquil1;


import android.content.Intent;
import android.media.MediaPlayer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MusicPage extends AppCompatActivity {
     MediaPlayer songone, songtwo, songthree, songfour, songfive, songsix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_page);

        songone = MediaPlayer.create(MusicPage.this, R.raw.songone);
        songtwo = MediaPlayer.create(MusicPage.this, R.raw.songtwo);
        songthree = MediaPlayer.create(MusicPage.this, R.raw.songthree);
        songfour = MediaPlayer.create(MusicPage.this, R.raw.songfour);
        songfive = MediaPlayer.create(MusicPage.this, R.raw.songfive);
        songsix = MediaPlayer.create(MusicPage.this, R.raw.songsix);

        Button b = (Button) findViewById(R.id.popButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MusicPage.this, Pop.class));

            }
        });
    } /*pop up message*/



    public void playIT1(View v){
        songone.start();
        songone.setLooping(true);

            Toast.makeText(getApplicationContext(), "SERENITY is playing now", Toast.LENGTH_LONG).show();

    }

    public void pauseIT1(View v){
        songone.pause();
    }
    public void stopIT1(View v) {
        songone.stop();

        songone = MediaPlayer.create(MusicPage.this, R.raw.songone);
    }


    public void playIT2(View v){
        songtwo.start();
        songtwo.setLooping(true);

        Toast.makeText(getApplicationContext(), "PEACE is playing now", Toast.LENGTH_LONG).show();
    }
    public void pauseIT2(View v){ songtwo.pause();
    }
    public void stopIT2(View v){
        songtwo.stop();
        songtwo = MediaPlayer.create(MusicPage.this, R.raw.songtwo);
    }

    public void playIT3(View v){
        songthree.start();
        songthree.setLooping(true);

        Toast.makeText(getApplicationContext(), "MISSING YOU is playing now", Toast.LENGTH_LONG).show();

    }
    public void pauseIT3(View v){ songthree.pause();
    }
    public void stopIT3(View v){
        songthree.stop();
        songthree = MediaPlayer.create(MusicPage.this, R.raw.songthree);

    }


    public void playIT4(View v){
        songfour.start();
        songfour.setLooping(true);

        Toast.makeText(getApplicationContext(), "DREAMING is playing now", Toast.LENGTH_LONG).show();

    }
    public void pauseIT4(View v){ songfour.pause();
    }
    public void stopIT4(View v){
        songfour.stop();
        songfour = MediaPlayer.create(MusicPage.this, R.raw.songfour);

    }


    public void playIT5(View v){
        songfive.start();
        songfive.setLooping(true);

        Toast.makeText(getApplicationContext(), "MY JOY is playing now", Toast.LENGTH_LONG).show();
    }
    public void pauseIT5(View v){ songfive.pause();
    }
    public void stopIT5(View v){
        songfive.stop();
        songfive = MediaPlayer.create(MusicPage.this, R.raw.songfive);

    }


    public void playIT6(View v){
        songsix.start();
        songsix.setLooping(true);

        Toast.makeText(getApplicationContext(), "DEPTH is playing now", Toast.LENGTH_LONG).show();
    }
    public void pauseIT6(View v){ songsix.pause();
    }
    public void stopIT6(View v){
        songsix.stop();
        songsix = MediaPlayer.create(MusicPage.this, R.raw.songsix);

    }
}


