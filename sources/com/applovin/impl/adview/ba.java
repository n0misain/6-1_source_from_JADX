package com.applovin.impl.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class ba implements OnErrorListener {
    /* renamed from: a */
    final /* synthetic */ az f238a;

    ba(az azVar) {
        this.f238a = azVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.f238a.f236a.f42v.post(new bb(this, i, i2));
        return true;
    }
}
