package com.example.tranquil1;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class AudioService extends Service {

    SharedPreference sharedPreference = new SharedPreference(this);
    MediaPlayer mediaPlayer = null;
    int raw = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        raw = intent.getIntExtra("rawId", 0);
        startPlaying(raw);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopPlaying();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void startPlaying(int putraw) {
        Handler handler = new Handler(Looper.getMainLooper());

        if (putraw != sharedPreference.getIntegerPreference("ID", 0)) {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            sharedPreference.setIntegerPreference("ID", putraw);
            mediaPlayer = new MediaPlayer();
            Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + putraw);
            try {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Audio Started", Toast.LENGTH_SHORT).show();
                    }
                });

                mediaPlayer.setDataSource(getApplicationContext(), mediaPath);
                mediaPlayer.prepare();
                mediaPlayer.start();

            } catch (IOException e) {
                Log.e("MEDIA PLAYER START", "prepare() failed");
            }
        } else if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Audio Paused", Toast.LENGTH_SHORT).show();
                }
            });

        } else if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Audio Play", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public void stopPlaying() {
        Handler handler = new Handler(Looper.getMainLooper());
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            handler.post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "No audio playing", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
