package com.google.android.gms.ads.internal.overlay;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View.MeasureSpec;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzagz;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzia;
import com.google.android.gms.internal.zzzn;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@zzzn
@TargetApi(14)
public final class zzd extends zzy implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener {
    private static final Map<Integer, String> zzOk = new HashMap();
    private final zzar zzOl;
    private final boolean zzOm;
    private int zzOn = 0;
    private int zzOo = 0;
    private MediaPlayer zzOp;
    private Uri zzOq;
    private int zzOr;
    private int zzOs;
    private int zzOt;
    private int zzOu;
    private int zzOv;
    private zzap zzOw;
    private boolean zzOx;
    private int zzOy;
    private zzx zzOz;

    static {
        if (VERSION.SDK_INT >= 17) {
            zzOk.put(Integer.valueOf(-1004), "MEDIA_ERROR_IO");
            zzOk.put(Integer.valueOf(-1007), "MEDIA_ERROR_MALFORMED");
            zzOk.put(Integer.valueOf(-1010), "MEDIA_ERROR_UNSUPPORTED");
            zzOk.put(Integer.valueOf(-110), "MEDIA_ERROR_TIMED_OUT");
            zzOk.put(Integer.valueOf(3), "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzOk.put(Integer.valueOf(100), "MEDIA_ERROR_SERVER_DIED");
        zzOk.put(Integer.valueOf(1), "MEDIA_ERROR_UNKNOWN");
        zzOk.put(Integer.valueOf(1), "MEDIA_INFO_UNKNOWN");
        zzOk.put(Integer.valueOf(AppLovinSdk.VERSION_CODE), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzOk.put(Integer.valueOf(701), "MEDIA_INFO_BUFFERING_START");
        zzOk.put(Integer.valueOf(702), "MEDIA_INFO_BUFFERING_END");
        zzOk.put(Integer.valueOf(800), "MEDIA_INFO_BAD_INTERLEAVING");
        zzOk.put(Integer.valueOf(801), "MEDIA_INFO_NOT_SEEKABLE");
        zzOk.put(Integer.valueOf(802), "MEDIA_INFO_METADATA_UPDATE");
        if (VERSION.SDK_INT >= 19) {
            zzOk.put(Integer.valueOf(901), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzOk.put(Integer.valueOf(902), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }

    public zzd(Context context, boolean z, boolean z2, zzaq zzaq, zzar zzar) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzOl = zzar;
        this.zzOx = z;
        this.zzOm = z2;
        this.zzOl.zza(this);
    }

    private final void zza(float f) {
        if (this.zzOp != null) {
            try {
                this.zzOp.setVolume(f, f);
                return;
            } catch (IllegalStateException e) {
                return;
            }
        }
        zzajc.zzaT("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
    }

    private final void zzfE() {
        Throwable e;
        String valueOf;
        zzafr.m1217v("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzOq != null && surfaceTexture != null) {
            zzq(false);
            try {
                SurfaceTexture zzgg;
                zzbs.zzbQ();
                this.zzOp = new MediaPlayer();
                this.zzOp.setOnBufferingUpdateListener(this);
                this.zzOp.setOnCompletionListener(this);
                this.zzOp.setOnErrorListener(this);
                this.zzOp.setOnInfoListener(this);
                this.zzOp.setOnPreparedListener(this);
                this.zzOp.setOnVideoSizeChangedListener(this);
                this.zzOt = 0;
                if (this.zzOx) {
                    this.zzOw = new zzap(getContext());
                    this.zzOw.zza(surfaceTexture, getWidth(), getHeight());
                    this.zzOw.start();
                    zzgg = this.zzOw.zzgg();
                    if (zzgg == null) {
                        this.zzOw.zzgf();
                        this.zzOw = null;
                    }
                    this.zzOp.setDataSource(getContext(), this.zzOq);
                    zzbs.zzbR();
                    this.zzOp.setSurface(new Surface(zzgg));
                    this.zzOp.setAudioStreamType(3);
                    this.zzOp.setScreenOnWhilePlaying(true);
                    this.zzOp.prepareAsync();
                    zzq(1);
                }
                zzgg = surfaceTexture;
                this.zzOp.setDataSource(getContext(), this.zzOq);
                zzbs.zzbR();
                this.zzOp.setSurface(new Surface(zzgg));
                this.zzOp.setAudioStreamType(3);
                this.zzOp.setScreenOnWhilePlaying(true);
                this.zzOp.prepareAsync();
                zzq(1);
            } catch (IOException e2) {
                e = e2;
                valueOf = String.valueOf(this.zzOq);
                zzajc.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzOp, 1, 0);
            } catch (IllegalArgumentException e3) {
                e = e3;
                valueOf = String.valueOf(this.zzOq);
                zzajc.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzOp, 1, 0);
            } catch (IllegalStateException e4) {
                e = e4;
                valueOf = String.valueOf(this.zzOq);
                zzajc.zzc(new StringBuilder(String.valueOf(valueOf).length() + 36).append("Failed to initialize MediaPlayer at ").append(valueOf).toString(), e);
                onError(this.zzOp, 1, 0);
            }
        }
    }

    private final void zzfF() {
        if (this.zzOm && zzfG() && this.zzOp.getCurrentPosition() > 0 && this.zzOo != 3) {
            zzafr.m1217v("AdMediaPlayerView nudging MediaPlayer");
            zza(0.0f);
            this.zzOp.start();
            int currentPosition = this.zzOp.getCurrentPosition();
            long currentTimeMillis = zzbs.zzbF().currentTimeMillis();
            while (zzfG() && this.zzOp.getCurrentPosition() == currentPosition) {
                if (zzbs.zzbF().currentTimeMillis() - currentTimeMillis > 250) {
                    break;
                }
            }
            this.zzOp.pause();
            zzfH();
        }
    }

    private final boolean zzfG() {
        return (this.zzOp == null || this.zzOn == -1 || this.zzOn == 0 || this.zzOn == 1) ? false : true;
    }

    private final void zzq(int i) {
        if (i == 3) {
            this.zzOl.zzgj();
            this.zzPq.zzgj();
        } else if (this.zzOn == 3) {
            this.zzOl.zzgk();
            this.zzPq.zzgk();
        }
        this.zzOn = i;
    }

    private final void zzq(boolean z) {
        zzafr.m1217v("AdMediaPlayerView release");
        if (this.zzOw != null) {
            this.zzOw.zzgf();
            this.zzOw = null;
        }
        if (this.zzOp != null) {
            this.zzOp.reset();
            this.zzOp.release();
            this.zzOp = null;
            zzq(0);
            if (z) {
                this.zzOo = 0;
                this.zzOo = 0;
            }
        }
    }

    public final int getCurrentPosition() {
        return zzfG() ? this.zzOp.getCurrentPosition() : 0;
    }

    public final int getDuration() {
        return zzfG() ? this.zzOp.getDuration() : -1;
    }

    public final int getVideoHeight() {
        return this.zzOp != null ? this.zzOp.getVideoHeight() : 0;
    }

    public final int getVideoWidth() {
        return this.zzOp != null ? this.zzOp.getVideoWidth() : 0;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzOt = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzafr.m1217v("AdMediaPlayerView completion");
        zzq(5);
        this.zzOo = 5;
        zzagz.zzZr.post(new zzf(this));
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzOk.get(Integer.valueOf(i));
        String str2 = (String) zzOk.get(Integer.valueOf(i2));
        zzajc.zzaT(new StringBuilder((String.valueOf(str).length() + 38) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(str).append(":").append(str2).toString());
        zzq(-1);
        this.zzOo = -1;
        zzagz.zzZr.post(new zzg(this, str, str2));
        return true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = (String) zzOk.get(Integer.valueOf(i));
        String str2 = (String) zzOk.get(Integer.valueOf(i2));
        zzafr.m1217v(new StringBuilder((String.valueOf(str).length() + 37) + String.valueOf(str2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(str).append(":").append(str2).toString());
        return true;
    }

    protected final void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.zzOr, i);
        int defaultSize2 = getDefaultSize(this.zzOs, i2);
        if (this.zzOr > 0 && this.zzOs > 0 && this.zzOw == null) {
            int mode = MeasureSpec.getMode(i);
            int size = MeasureSpec.getSize(i);
            int mode2 = MeasureSpec.getMode(i2);
            defaultSize2 = MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                if (this.zzOr * defaultSize2 < this.zzOs * size) {
                    defaultSize = (this.zzOr * defaultSize2) / this.zzOs;
                } else if (this.zzOr * defaultSize2 > this.zzOs * size) {
                    defaultSize2 = (this.zzOs * size) / this.zzOr;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode == 1073741824) {
                defaultSize = (this.zzOs * size) / this.zzOr;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = size;
                } else {
                    defaultSize = size;
                }
            } else if (mode2 == 1073741824) {
                defaultSize = (this.zzOr * defaultSize2) / this.zzOs;
                if (mode == Integer.MIN_VALUE && defaultSize > size) {
                    defaultSize = size;
                }
            } else {
                int i3 = this.zzOr;
                defaultSize = this.zzOs;
                if (mode2 != Integer.MIN_VALUE || defaultSize <= defaultSize2) {
                    defaultSize2 = defaultSize;
                    defaultSize = i3;
                } else {
                    defaultSize = (this.zzOr * defaultSize2) / this.zzOs;
                }
                if (mode == Integer.MIN_VALUE && r1 > size) {
                    defaultSize2 = (this.zzOs * size) / this.zzOr;
                    defaultSize = size;
                }
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        if (this.zzOw != null) {
            this.zzOw.zzf(defaultSize, defaultSize2);
        }
        if (VERSION.SDK_INT == 16) {
            if ((this.zzOu > 0 && this.zzOu != defaultSize) || (this.zzOv > 0 && this.zzOv != defaultSize2)) {
                zzfF();
            }
            this.zzOu = defaultSize;
            this.zzOv = defaultSize2;
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzafr.m1217v("AdMediaPlayerView prepared");
        zzq(2);
        this.zzOl.zzfT();
        zzagz.zzZr.post(new zze(this));
        this.zzOr = mediaPlayer.getVideoWidth();
        this.zzOs = mediaPlayer.getVideoHeight();
        if (this.zzOy != 0) {
            seekTo(this.zzOy);
        }
        zzfF();
        int i = this.zzOr;
        zzajc.zzaS("AdMediaPlayerView stream dimensions: " + i + " x " + this.zzOs);
        if (this.zzOo == 3) {
            play();
        }
        zzfH();
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzafr.m1217v("AdMediaPlayerView surface created");
        zzfE();
        zzagz.zzZr.post(new zzh(this));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzafr.m1217v("AdMediaPlayerView surface destroyed");
        if (this.zzOp != null && this.zzOy == 0) {
            this.zzOy = this.zzOp.getCurrentPosition();
        }
        if (this.zzOw != null) {
            this.zzOw.zzgf();
        }
        zzagz.zzZr.post(new zzj(this));
        zzq(true);
        return true;
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        Object obj = 1;
        zzafr.m1217v("AdMediaPlayerView surface changed");
        Object obj2 = this.zzOo == 3 ? 1 : null;
        if (!(this.zzOr == i && this.zzOs == i2)) {
            obj = null;
        }
        if (!(this.zzOp == null || obj2 == null || r1 == null)) {
            if (this.zzOy != 0) {
                seekTo(this.zzOy);
            }
            play();
        }
        if (this.zzOw != null) {
            this.zzOw.zzf(i, i2);
        }
        zzagz.zzZr.post(new zzi(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzOl.zzb(this);
        this.zzPp.zza(surfaceTexture, this.zzOz);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zzafr.m1217v("AdMediaPlayerView size changed: " + i + " x " + i2);
        this.zzOr = mediaPlayer.getVideoWidth();
        this.zzOs = mediaPlayer.getVideoHeight();
        if (this.zzOr != 0 && this.zzOs != 0) {
            requestLayout();
        }
    }

    public final void pause() {
        zzafr.m1217v("AdMediaPlayerView pause");
        if (zzfG() && this.zzOp.isPlaying()) {
            this.zzOp.pause();
            zzq(4);
            zzagz.zzZr.post(new zzl(this));
        }
        this.zzOo = 4;
    }

    public final void play() {
        zzafr.m1217v("AdMediaPlayerView play");
        if (zzfG()) {
            this.zzOp.start();
            zzq(3);
            this.zzPp.zzfU();
            zzagz.zzZr.post(new zzk(this));
        }
        this.zzOo = 3;
    }

    public final void seekTo(int i) {
        zzafr.m1217v("AdMediaPlayerView seek " + i);
        if (zzfG()) {
            this.zzOp.seekTo(i);
            this.zzOy = 0;
            return;
        }
        this.zzOy = i;
    }

    public final void setVideoPath(String str) {
        Uri parse = Uri.parse(str);
        zzia zze = zzia.zze(parse);
        if (zze != null) {
            parse = Uri.parse(zze.url);
        }
        this.zzOq = parse;
        this.zzOy = 0;
        zzfE();
        requestLayout();
        invalidate();
    }

    public final void stop() {
        zzafr.m1217v("AdMediaPlayerView stop");
        if (this.zzOp != null) {
            this.zzOp.stop();
            this.zzOp.release();
            this.zzOp = null;
            zzq(0);
            this.zzOo = 0;
        }
        this.zzOl.onStop();
    }

    public final String toString() {
        String valueOf = String.valueOf(getClass().getName());
        String valueOf2 = String.valueOf(Integer.toHexString(hashCode()));
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("@").append(valueOf2).toString();
    }

    public final void zza(float f, float f2) {
        if (this.zzOw != null) {
            this.zzOw.zzb(f, f2);
        }
    }

    public final void zza(zzx zzx) {
        this.zzOz = zzx;
    }

    public final String zzfD() {
        String str = "MediaPlayer";
        String valueOf = String.valueOf(this.zzOx ? " spherical" : "");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public final void zzfH() {
        zza(this.zzPq.zzgm());
    }
}
