package com.google.android.gms.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzajc;
import com.google.android.gms.internal.zzks;
import com.google.android.gms.internal.zzlw;
import com.google.android.gms.internal.zzzn;

@zzzn
public final class VideoController {
    @KeepForSdk
    public static final int PLAYBACK_STATE_ENDED = 3;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PAUSED = 2;
    @KeepForSdk
    public static final int PLAYBACK_STATE_PLAYING = 1;
    @KeepForSdk
    public static final int PLAYBACK_STATE_READY = 5;
    @KeepForSdk
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object mLock = new Object();
    @Nullable
    private zzks zzsd;
    @Nullable
    private VideoLifecycleCallbacks zzse;

    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    public final float getAspectRatio() {
        float f = 0.0f;
        synchronized (this.mLock) {
            if (this.zzsd == null) {
            } else {
                try {
                    f = this.zzsd.getAspectRatio();
                } catch (Throwable e) {
                    zzajc.zzb("Unable to call getAspectRatio on video controller.", e);
                }
            }
        }
        return f;
    }

    @KeepForSdk
    public final int getPlaybackState() {
        int i = 0;
        synchronized (this.mLock) {
            if (this.zzsd == null) {
            } else {
                try {
                    i = this.zzsd.getPlaybackState();
                } catch (Throwable e) {
                    zzajc.zzb("Unable to call getPlaybackState on video controller.", e);
                }
            }
        }
        return i;
    }

    @Nullable
    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.mLock) {
            videoLifecycleCallbacks = this.zzse;
        }
        return videoLifecycleCallbacks;
    }

    public final boolean hasVideoContent() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzsd != null;
        }
        return z;
    }

    @KeepForSdk
    public final boolean isCustomControlsEnabled() {
        boolean z = false;
        synchronized (this.mLock) {
            if (this.zzsd == null) {
            } else {
                try {
                    z = this.zzsd.isCustomControlsEnabled();
                } catch (Throwable e) {
                    zzajc.zzb("Unable to call isUsingCustomPlayerControls.", e);
                }
            }
        }
        return z;
    }

    @KeepForSdk
    public final boolean isMuted() {
        boolean z = true;
        synchronized (this.mLock) {
            if (this.zzsd == null) {
            } else {
                try {
                    z = this.zzsd.isMuted();
                } catch (Throwable e) {
                    zzajc.zzb("Unable to call isMuted on video controller.", e);
                }
            }
        }
        return z;
    }

    @KeepForSdk
    public final void mute(boolean z) {
        synchronized (this.mLock) {
            if (this.zzsd == null) {
                return;
            }
            try {
                this.zzsd.mute(z);
            } catch (Throwable e) {
                zzajc.zzb("Unable to call mute on video controller.", e);
            }
        }
    }

    @KeepForSdk
    public final void pause() {
        synchronized (this.mLock) {
            if (this.zzsd == null) {
                return;
            }
            try {
                this.zzsd.pause();
            } catch (Throwable e) {
                zzajc.zzb("Unable to call pause on video controller.", e);
            }
        }
    }

    @KeepForSdk
    public final void play() {
        synchronized (this.mLock) {
            if (this.zzsd == null) {
                return;
            }
            try {
                this.zzsd.play();
            } catch (Throwable e) {
                zzajc.zzb("Unable to call play on video controller.", e);
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        zzbo.zzb((Object) videoLifecycleCallbacks, (Object) "VideoLifecycleCallbacks may not be null.");
        synchronized (this.mLock) {
            this.zzse = videoLifecycleCallbacks;
            if (this.zzsd == null) {
                return;
            }
            try {
                this.zzsd.zza(new zzlw(videoLifecycleCallbacks));
            } catch (Throwable e) {
                zzajc.zzb("Unable to call setVideoLifecycleCallbacks on video controller.", e);
            }
        }
    }

    public final void zza(zzks zzks) {
        synchronized (this.mLock) {
            this.zzsd = zzks;
            if (this.zzse != null) {
                setVideoLifecycleCallbacks(this.zzse);
            }
        }
    }

    public final zzks zzae() {
        zzks zzks;
        synchronized (this.mLock) {
            zzks = this.zzsd;
        }
        return zzks;
    }
}
