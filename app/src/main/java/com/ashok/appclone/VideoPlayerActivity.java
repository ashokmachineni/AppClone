package com.ashok.appclone;

/**
 * Created by ashok on 9/13/16.
 */

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {

    public static String pathToFileOrUrl ;
    private VideoView mVideoView;
    private ProgressBar load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.initialize(getBaseContext());
        setContentView(R.layout.videoplayeractivity);


        if (!LibsChecker.checkVitamioLibs(this))
            return;


        mVideoView = (VideoView) findViewById(R.id.surface_view);



            /*
             * Alternatively,for streaming media you can use
             * mVideoView.setVideoURI(Uri.parse(URLstring));
             */
        mVideoView.setVideoURI(Uri.parse(pathToFileOrUrl));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                load=(ProgressBar) findViewById(R.id.loading);
                load.setVisibility(View.GONE);
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });

        getSupportActionBar().hide();

    }

    public void startPlay(View view) {
        if (!TextUtils.isEmpty(pathToFileOrUrl)) {
            mVideoView.setVideoPath(pathToFileOrUrl);
        }
    }

    public void openVideo(View View) {
        mVideoView.setVideoPath(pathToFileOrUrl);
    }

}
