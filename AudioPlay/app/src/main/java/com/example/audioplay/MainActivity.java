package com.example.audioplay;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    RelativeLayout rLayout;
    String strAudioPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rLayout = findViewById(R.id.rl_main);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPause = findViewById(R.id.btnPause);
        Button btnStop = findViewById(R.id.btnStop);

        prepareMedia();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });
    }

    void prepareMedia(){
        try {
            strAudioPath = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";
            // intializing the media player
            mediaPlayer = new MediaPlayer();

            // set the audio stream type to the media player
            mediaPlayer.setAudioAttributes(
                    new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
            );
            // set the source of audio to the media player
            mediaPlayer.setDataSource(strAudioPath);

            // prepare the media player
            mediaPlayer.prepare();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void playAudio(){
        if(mediaPlayer == null){
            prepareMedia();
            mediaPlayer.start();
        }else if(!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
        Snackbar.make(rLayout, "Audio is playing", Snackbar.LENGTH_LONG).show();
    }

    void pauseAudio(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            Snackbar.make(rLayout, "Audio Pause", Snackbar.LENGTH_LONG).show();
        }else {
            Snackbar.make(rLayout, "Audio is not playing", Snackbar.LENGTH_LONG).show();
        }
    }

    void stopAudio(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
            Snackbar.make(rLayout, "Audio Stop", Snackbar.LENGTH_LONG).show();
        }else {
            Snackbar.make(rLayout, "Audio is not playing", Snackbar.LENGTH_LONG).show();
        }
    }
}