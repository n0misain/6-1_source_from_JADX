package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class bc implements OnCompletionListener {
    /* renamed from: a */
    final /* synthetic */ aj f242a;

    bc(aj ajVar) {
        this.f242a = ajVar;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.f242a.m71i();
    }
}
