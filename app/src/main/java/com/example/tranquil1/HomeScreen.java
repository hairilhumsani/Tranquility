package com.example.tranquil1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;


public class HomeScreen extends Activity {

    private SeekBar volumeSeekBar;
    private AudioManager audioManager;
    private static TextView text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
    }

    public void goToMeditation(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MeditationPage.class);
        startActivity(intent);
    }

    public void goToMusic(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MusicPage.class);
        startActivity(intent);
    }

    public void goToAsmr(View view)
    {
        Intent intent = new Intent(getApplicationContext(), AsmrPage.class);
        startActivity(intent);
    }

    public void goToTinnitus(View view)
    {
        Intent intent = new Intent(getApplicationContext(),TinnitusPage.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(HomeScreen.this);

        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Alert!");

        builder.setCancelable(false);

        builder
                .setPositiveButton(
                        "Yes",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                finish();
                            }
                        });
        builder
                .setNegativeButton(
                        "No",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {


                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void enableseekbar(View view)
    {
        View b = findViewById(R.id.volumebutton);
        b.setVisibility(View.GONE);

        View seekBar = findViewById(R.id.volumeSeekBar);
        seekBar.setVisibility(View.VISIBLE);

        View cancelbutton=findViewById(R.id.cancelbutton);
        cancelbutton.setVisibility(View.VISIBLE);

        View textView25 = findViewById(R.id.textView25);
        textView25.setVisibility(View.VISIBLE);

    }

    public void cancelvolume(View view)
    {
        View seekBar = findViewById(R.id.volumeSeekBar);
        seekBar.setVisibility(View.INVISIBLE);

        View cancelbutton=findViewById(R.id.cancelbutton);
        cancelbutton.setVisibility(View.INVISIBLE);

        View b = findViewById(R.id.volumebutton);
        b.setVisibility(View.VISIBLE);

        View textView25 = findViewById(R.id.textView25);
        textView25.setVisibility(View.INVISIBLE);
    }


    private void initControls() {
        try {
            volumeSeekBar = (SeekBar) findViewById(R.id.volumeSeekBar);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            text_view= (TextView) findViewById(R.id.textView25);
            text_view.setText("volume: " + volumeSeekBar.getProgress()+ "%" +" / "+volumeSeekBar.getMax() + "%");


            volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progress_value;

                @Override
                public void onProgressChanged(SeekBar volumeSeekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    progress_value= progress;
                    text_view.setText("volume: " + volumeSeekBar.getProgress()+ "%" +" / "+volumeSeekBar.getMax() + "%");
                }


                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    text_view.setText("Volume: " + progress_value +"%" +" / "+volumeSeekBar.getMax() + "%");
                }
            });
        } catch (Exception e) {
        }
    }

}
