package com.example.pedrortsp_androiddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.videolan.libvlc.util.VLCVideoLayout;


public class MainActivity extends Activity {

    private static final String[] urlList = {
            "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4"
    };
    private static final String[] cameraList = {
            "Camera 1"
    };

    private VideoStreamPlayer streamPlayer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VLCVideoLayout videoLayout = findViewById(R.id.videoLayout);
        streamPlayer = new VideoStreamPlayer(this, videoLayout);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                cameraList
        );

        Spinner dropdown = findViewById(R.id.cameraDropdown);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos, long id)
            {
                streamPlayer.stop();
                streamPlayer.start(urlList[pos]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        streamPlayer.stop();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        streamPlayer.release();
    }


}