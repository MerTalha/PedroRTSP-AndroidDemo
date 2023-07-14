package com.example.pedrortsp_androiddemo;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback, SamplePlayer.OnVideoSizeChangedListener {

    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private RtspPlayer rtspPlayer;
    private String rtspUrl = "rtsp://DVR_KAMERANIN_IP_ADRESI:554/STREAM_PATH";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback((SurfaceHolder.Callback) this);

        rtspPlayer = new RtspPlayer();
        rtspPlayer.setOnVideoSizeChangedListener(this);

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        rtspPlayer.setSurfaceHolder(holder);
        rtspPlayer.setDisplaySize(surfaceView.getWidth(), surfaceView.getHeight());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        rtspPlayer.setSurfaceHolder(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rtspPlayer.setRtspUrl(rtspUrl);
        rtspPlayer.play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        rtspPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rtspPlayer.stop();
    }

    @Override
    public void onVideoSizeChanged(int width, int height) {
        // Video boyutu değiştiğinde yapılacak işlemler
    }
}