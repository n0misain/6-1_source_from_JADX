package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

class az implements OnPreparedListener {
    /* renamed from: a */
    final /* synthetic */ aj f236a;

    az(aj ajVar) {
        this.f236a = ajVar;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.f236a.f16D = new WeakReference(mediaPlayer);
        int i = this.f236a.m74j() ? 0 : 1;
        mediaPlayer.setVolume((float) i, (float) i);
        i = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.f236a.computedLengthSeconds = (int) TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getDuration());
        this.f236a.videoView.setVideoSize(i, videoHeight);
        mediaPlayer.setDisplay(this.f236a.videoView.getHolder());
        mediaPlayer.setOnErrorListener(new ba(this));
        if (this.f236a.f39s == 0) {
            this.f236a.m89r();
            this.f236a.m77l();
            this.f236a.m99w();
            this.f236a.m98v();
            this.f236a.playVideo();
        }
    }
}
