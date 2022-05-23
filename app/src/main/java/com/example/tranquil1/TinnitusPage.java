package com.example.tranquil1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class TinnitusPage extends AppCompatActivity {
    private SeekBar seek_bar;
    private TextView text_view;
    Button buttonPlay;

    MediaPlayer frequencies = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinnitus_page);
        seek_bar = findViewById(R.id.seekBar);
        buttonPlay = findViewById(R.id.btnPlay);

        Button b = (Button) findViewById(R.id.popTinBut);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TinnitusPage.this, PopTinnitus.class));

            }
        });
    }

    private void startPlaying() {

        final Handler handler = new Handler();
        if(frequencies != null && frequencies.isPlaying()){
            frequencies.pause();
        } else if(frequencies != null){
            frequencies.start();
        }else{
            frequencies = new MediaPlayer();
            Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.frequencies);
            try {

                frequencies.setDataSource(getApplicationContext(),mediaPath);
                frequencies.prepare();
                frequencies.start();

            } catch (IOException e) {
                Log.e("MEDIA PLAYER START", "prepare() failed");
            }
        }

        seek_bar.setMax(frequencies.getDuration()/1000);

        TinnitusPage.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(frequencies !=null )
                {
                    int mCurrentPosition = frequencies.getCurrentPosition()/1000;
                    seek_bar.setProgress(mCurrentPosition);
                }
                handler.postDelayed(this,1000);
            }
        });

        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (frequencies != null && fromUser)
                {
                        frequencies.seekTo(progress*1000);

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void stopPlaying() {
        if (frequencies != null)
        {
            frequencies.release();
            frequencies = null;
        }
        else if (frequencies == null)
        {
            Toast.makeText(getApplicationContext(),"No audio playing",Toast.LENGTH_SHORT).show();
        }

    }

    private void pausePlaying(){
        if(frequencies.isPlaying()){
            frequencies.pause();
        } else {
            frequencies.start();
        }
    }

    public void btnPlay(View v){

        if (buttonPlay.getText().equals("PLAY"))
        {
            startPlaying();
            buttonPlay.setText("PAUSE");
        }
        else {
            pausePlaying();
            buttonPlay.setText("PLAY");
        }

    }

    public void btnStop(View v){
        stopPlaying();
        seek_bar.setProgress(0);
        buttonPlay.setText("PLAY");
    }
}
