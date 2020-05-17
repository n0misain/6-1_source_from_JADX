package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class bd implements OnErrorListener {
    /* renamed from: a */
    final /* synthetic */ aj f243a;

    bd(aj ajVar) {
        this.f243a = ajVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f243a.f42v.post(new be(this, i, i2));
        return true;
    }
}
