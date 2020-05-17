package com.applovin.impl.adview;

import android.net.Uri;
import android.os.Bundle;
import com.applovin.impl.p000a.C0450a;
import com.applovin.impl.p000a.C0454e;
import com.applovin.impl.p000a.C0457h;
import com.applovin.impl.p000a.C0461l;
import com.applovin.impl.p000a.C0462m;
import com.applovin.impl.p000a.C0463n;
import com.applovin.impl.p000a.C0467r;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class cc extends aj {
    /* renamed from: a */
    private final Set f47a = new HashSet();

    /* renamed from: a */
    private void m107a() {
        if (isFullyWatched() && !this.f47a.isEmpty()) {
            this.logger.mo2294w("InterstitialActivity", "Firing " + this.f47a.size() + " un-fired video progress trackers when video was completed.");
            m112a(this.f47a);
        }
    }

    /* renamed from: a */
    private void m108a(C0454e c0454e) {
        m109a(c0454e, C0457h.UNSPECIFIED);
    }

    /* renamed from: a */
    private void m109a(C0454e c0454e, C0457h c0457h) {
        m111a(c0454e, "", c0457h);
    }

    /* renamed from: a */
    private void m110a(C0454e c0454e, String str) {
        m111a(c0454e, str, C0457h.UNSPECIFIED);
    }

    /* renamed from: a */
    private void m111a(C0454e c0454e, String str, C0457h c0457h) {
        if (m114b()) {
            m113a(((C0450a) this.currentAd).m167a(c0454e, str), c0457h);
        }
    }

    /* renamed from: a */
    private void m112a(Set set) {
        m113a(set, C0457h.UNSPECIFIED);
    }

    /* renamed from: a */
    private void m113a(Set set, C0457h c0457h) {
        if (m114b() && set != null && !set.isEmpty()) {
            long toSeconds = TimeUnit.MILLISECONDS.toSeconds((long) this.videoView.getCurrentPosition());
            C0467r c = m115c().m172c();
            Uri a = c != null ? c.m256a() : null;
            this.logger.mo2288d("InterstitialActivity", "Firing " + set.size() + " tracker(s): " + set);
            C0463n.m236a(set, toSeconds, a, c0457h, this.sdk);
        }
    }

    /* renamed from: b */
    private boolean m114b() {
        return this.currentAd instanceof C0450a;
    }

    /* renamed from: c */
    private C0450a m115c() {
        return this.currentAd instanceof C0450a ? (C0450a) this.currentAd : null;
    }

    public void clickThroughFromVideo() {
        super.clickThroughFromVideo();
        m108a(C0454e.VIDEO_CLICK);
    }

    public void dismiss() {
        if (m114b()) {
            m110a(C0454e.VIDEO, "close");
            m110a(C0454e.COMPANION, "close");
        }
        super.dismiss();
    }

    public void handleCountdownStep() {
        if (m114b()) {
            long toSeconds = ((long) this.computedLengthSeconds) - TimeUnit.MILLISECONDS.toSeconds((long) (this.videoView.getDuration() - this.videoView.getCurrentPosition()));
            Set hashSet = new HashSet();
            for (C0461l c0461l : new HashSet(this.f47a)) {
                if (c0461l.m223a(toSeconds, getVideoPercentViewed())) {
                    hashSet.add(c0461l);
                    this.f47a.remove(c0461l);
                }
            }
            m112a(hashSet);
        }
    }

    public void handleMediaError() {
        m109a(C0454e.ERROR, C0457h.MEDIA_FILE_ERROR);
        super.handleMediaError();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (m114b()) {
            String a = m115c().m166a(this.currentPlacement);
            if (AppLovinSdkUtils.isValidString(a)) {
                this.logger.mo2288d("InterstitialActivity", "Firing AppLovin impression...");
                this.sdk.getPersistentPostbackManager().m763a(a, null, false);
            }
            this.f47a.addAll(m115c().m168a(C0454e.VIDEO, C0462m.f135a));
            m108a(C0454e.IMPRESSION);
            m110a(C0454e.VIDEO, "creativeView");
        }
    }

    public void playVideo() {
        this.countdownManager.m438a("PROGRESS_TRACKING", this.settingsProxy.ag(), new cd(this));
        super.playVideo();
    }

    public void showPoststitial() {
        if (m114b()) {
            m107a();
            if (!C0463n.m243c(m115c())) {
                dismiss();
                return;
            } else if (!this.poststitialWasDisplayed) {
                m110a(C0454e.COMPANION, "creativeView");
                super.showPoststitial();
                return;
            } else {
                return;
            }
        }
        super.showPoststitial();
    }

    public void skipVideo() {
        m110a(C0454e.VIDEO, "skip");
        super.skipVideo();
    }

    public void toggleMute() {
        super.toggleMute();
        if (this.videoMuted) {
            m110a(C0454e.VIDEO, "mute");
        } else {
            m110a(C0454e.VIDEO, "unmute");
        }
    }
}
