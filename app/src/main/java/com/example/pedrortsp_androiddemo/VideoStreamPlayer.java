package com.example.pedrortsp_androiddemo;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import org.videolan.libvlc.LibVLC;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;

public class VideoStreamPlayer extends Activity {
    private final LibVLC libVlc;
    private final MediaPlayer mediaPlayer;
    private final VLCVideoLayout videoLayout;

    public VideoStreamPlayer(Context context, VLCVideoLayout videoLayout)
    {
        libVlc = new LibVLC(context);
        mediaPlayer = new MediaPlayer(libVlc);
        this.videoLayout = videoLayout;
    }

    public void start(String url)
    {
        mediaPlayer.attachViews(videoLayout, null, false, false);

        Media media = new Media(libVlc, Uri.parse(url));
        media.setHWDecoderEnabled(true, false);
        media.addOption(":codec=mediacodec,iomx,all");
        media.addOption(":vcodec=h264");
        media.addOption(":network-caching=110");
        media.addOption(":clock-jitter=0");
        media.addOption(":clock-synchro=0");
        media.addOption(":clock-quantization=1");
        media.addOption(":skip-frames");
        /*media.addOption(":file-caching=130");
        media.addOption(":live-caching=130");
        media.addOption(":sout-mux-caching=130");*/


        /*
        ArrayList<String> options = new ArrayList<String>();
        options.add("--aout=opensles");
        options.add("--audio-time-stretch"); // time stretching
        options.add("-vvv"); // verbosity
        libVlc = new LibVLC(MainActivity.this, options);
        int cache = 1500;
        1-
        media.addOption("--aout=opensles");
        2-
        media.addOption("--audio-time-stretch"); // time stretching
        3-
        media.addOption("-vvv"); // verbosity
        4-
        media.addOption("--aout=opensles");
        5-
        media.addOption("--avcodec-codec=h264");
        6-
        media.addOption("--file-logging");
        7-
        media.addOption("--logfile=vlc-log.txt");
        8-
        media.addOption(":network-caching=" + cache);
        9-
        media.addOption(":file-caching=" + cache);
        10-
        media.addOption(":live-cacheing=" + cache);
        11-
        media.addOption(":sout-mux-caching=" + cache);
        12-
        media.addOption(":codec=mediacodec,iomx,all");
        */
        mediaPlayer.setMedia(media);
        media.release();
        mediaPlayer.play();
    }

    public void stop()
    {
        mediaPlayer.stop();
        mediaPlayer.detachViews();
    }

    public void release()
    {
        mediaPlayer.release();
        libVlc.release();
    }
}
