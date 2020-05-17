package com.applovin.impl.adview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.ColorStateList;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinTouchToClickListener;
import com.applovin.impl.sdk.AppLovinAdServiceImpl;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.C0503g;
import com.applovin.impl.sdk.C0516u;
import com.applovin.impl.sdk.ae;
import com.applovin.impl.sdk.ag;
import com.applovin.impl.sdk.ah;
import com.applovin.impl.sdk.bl;
import com.applovin.impl.sdk.dn;
import com.applovin.impl.sdk.dp;
import com.applovin.impl.sdk.fk;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class aj extends Activity implements ad {
    public static final String KEY_WRAPPER_ID = "com.applovin.interstitial.wrapper_id";
    public static volatile bt lastKnownWrapper = null;
    /* renamed from: A */
    private View f13A;
    /* renamed from: B */
    private C0491v f14B;
    /* renamed from: C */
    private ImageView f15C;
    /* renamed from: D */
    private WeakReference f16D = new WeakReference(null);
    /* renamed from: E */
    private bl f17E;
    /* renamed from: F */
    private cf f18F;
    /* renamed from: G */
    private ProgressBar f19G;
    /* renamed from: H */
    private ch f20H;
    /* renamed from: a */
    private AppLovinAdView f21a;
    /* renamed from: b */
    private bt f22b;
    /* renamed from: c */
    private volatile boolean f23c = false;
    protected int computedLengthSeconds = 0;
    protected C0494z countdownManager;
    public volatile ae currentAd;
    public String currentPlacement;
    /* renamed from: d */
    private dp f24d;
    /* renamed from: e */
    private volatile boolean f25e = false;
    /* renamed from: f */
    private volatile boolean f26f = false;
    /* renamed from: g */
    private volatile boolean f27g = false;
    /* renamed from: h */
    private volatile boolean f28h = false;
    /* renamed from: i */
    private volatile boolean f29i = false;
    /* renamed from: j */
    private volatile boolean f30j = false;
    /* renamed from: k */
    private volatile boolean f31k = false;
    /* renamed from: l */
    private volatile boolean f32l = false;
    public AppLovinLogger logger;
    /* renamed from: m */
    private boolean f33m = false;
    /* renamed from: n */
    private volatile boolean f34n = false;
    /* renamed from: o */
    private boolean f35o = true;
    /* renamed from: p */
    private boolean f36p = false;
    protected volatile boolean poststitialWasDisplayed = false;
    /* renamed from: q */
    private boolean f37q = false;
    /* renamed from: r */
    private long f38r = 0;
    /* renamed from: s */
    private long f39s = 0;
    public AppLovinSdkImpl sdk;
    public dn settingsProxy;
    /* renamed from: t */
    private int f40t = 0;
    /* renamed from: u */
    private AtomicBoolean f41u = new AtomicBoolean(false);
    /* renamed from: v */
    private Handler f42v;
    protected volatile boolean videoMuted = false;
    public AppLovinVideoView videoView;
    /* renamed from: w */
    private FrameLayout f43w;
    /* renamed from: x */
    private C0471x f44x;
    /* renamed from: y */
    private View f45y;
    /* renamed from: z */
    private C0471x f46z;

    /* renamed from: A */
    private boolean m18A() {
        return m24E() && !isFullyWatched() && this.settingsProxy.m838N() && this.f17E != null;
    }

    /* renamed from: B */
    private boolean m20B() {
        return m25F() && !m23D() && this.settingsProxy.m839O() && this.f17E != null;
    }

    /* renamed from: C */
    private int m21C() {
        if (!(this.currentAd instanceof C0503g)) {
            return 0;
        }
        float g = ((C0503g) this.currentAd).m1126g();
        if (g <= 0.0f) {
            g = this.currentAd.m149p();
        }
        return (int) Math.min((fk.m1075a(System.currentTimeMillis() - this.f38r) / ((double) g)) * 100.0d, 100.0d);
    }

    /* renamed from: D */
    private boolean m23D() {
        return m21C() >= 95;
    }

    /* renamed from: E */
    private boolean m24E() {
        return AppLovinAdType.INCENTIVIZED.equals(this.currentAd.getType());
    }

    /* renamed from: F */
    private boolean m25F() {
        return !this.currentAd.isVideoAd() && m24E();
    }

    /* renamed from: G */
    private void m26G() {
        if (this.f23c && !this.f37q) {
            return;
        }
        if (this.f21a != null) {
            this.f21a.setAdDisplayListener(new aw(this));
            this.f21a.setAdClickListener(new ax(this));
            this.currentAd = (ae) this.f22b.m358b();
            m43a(this.currentAd);
            m75k();
            this.f34n = this.currentAd.mo2134b();
            if (this.f34n) {
                this.logger.mo2288d("InterActivity", "Preparing stream for " + this.currentAd.mo2135d());
            } else {
                this.logger.mo2288d("InterActivity", "Preparing cached video playback for " + this.currentAd.mo2135d());
            }
            m38a(this.currentAd.mo2135d());
            this.f44x.bringToFront();
            if (m83o() && this.f45y != null) {
                this.f45y.bringToFront();
            }
            if (this.f46z != null) {
                this.f46z.bringToFront();
            }
            this.f21a.renderAd(this.currentAd, this.currentPlacement);
            this.f22b.m357a(true);
            if (!this.currentAd.isVideoAd()) {
                if (m25F() && this.settingsProxy.m844T()) {
                    m62d(this.currentAd);
                }
                showPoststitial();
                return;
            }
            return;
        }
        exitWithError("AdView was null");
    }

    /* renamed from: H */
    private boolean m27H() {
        return this.videoMuted;
    }

    /* renamed from: I */
    private void m28I() {
        Editor edit = m32M().edit();
        edit.putInt("com.applovin.interstitial.last_video_position", this.videoView.getCurrentPosition());
        edit.putBoolean("com.applovin.interstitial.should_resume_video", true);
        edit.commit();
        try {
            this.countdownManager.m440c();
        } catch (Throwable th) {
            this.logger.mo2290e("InterActivity", "Unable to pause countdown timers", th);
        }
        this.videoView.pause();
    }

    /* renamed from: J */
    private void m29J() {
        long max = Math.max(0, new dn(this.sdk).ak());
        if (max > 0) {
            this.sdk.getLogger().mo2288d("InterActivity", "Resuming video with delay of " + max);
            this.f42v.postDelayed(new ay(this), max);
            return;
        }
        this.sdk.getLogger().mo2288d("InterActivity", "Resuming video immediately");
        m30K();
    }

    /* renamed from: K */
    private void m30K() {
        SharedPreferences M = m32M();
        if (this.videoView != null && !this.videoView.isPlaying()) {
            this.videoView.seekTo(M.getInt("com.applovin.interstitial.last_video_position", this.videoView.getDuration()));
            this.videoView.start();
            this.countdownManager.m437a();
        }
    }

    /* renamed from: L */
    private void m31L() {
        if (!this.f29i) {
            try {
                if (this.currentAd.isVideoAd()) {
                    double videoPercentViewed = (double) getVideoPercentViewed();
                    String a = this.currentAd.m138a((int) videoPercentViewed, this.currentPlacement, this.f34n);
                    if (AppLovinSdkUtils.isValidString(a)) {
                        this.sdk.getPostbackService().dispatchPostbackAsync(a, null);
                    } else {
                        this.logger.mo2289e("InterActivity", "Received invalid placement aware parameterized video completion url. No postback dispatched.");
                    }
                    m45a(this.currentAd, videoPercentViewed, isFullyWatched());
                } else if ((this.currentAd instanceof C0503g) && m25F() && this.settingsProxy.m844T()) {
                    int C = m21C();
                    this.logger.mo2288d("InterActivity", "Rewarded playable engaged at " + C + " percent");
                    m45a(this.currentAd, (double) C, C >= 95);
                }
            } catch (Throwable th) {
                if (this.logger != null) {
                    this.logger.mo2290e("InterActivity", "Failed to notify end listener.", th);
                }
            }
        }
    }

    /* renamed from: M */
    private SharedPreferences m32M() {
        return getSharedPreferences("com.applovin.interstitial.sharedpreferences", 0);
    }

    /* renamed from: a */
    private int m33a(int i) {
        return AppLovinSdkUtils.dpToPx(this, i);
    }

    /* renamed from: a */
    private int m34a(int i, boolean z) {
        if (z) {
            if (i == 0) {
                return 0;
            }
            if (i == 1) {
                return 9;
            }
            if (i == 2) {
                return 8;
            }
            if (i == 3) {
                return 1;
            }
        } else if (i == 0) {
            return 1;
        } else {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 9;
            }
            if (i == 3) {
                return 8;
            }
        }
        return -1;
    }

    /* renamed from: a */
    private static int m35a(Display display) {
        return display.getWidth() == display.getHeight() ? 3 : display.getWidth() < display.getHeight() ? 1 : 2;
    }

    /* renamed from: a */
    private void m37a(long j, C0471x c0471x) {
        this.f42v.postDelayed(new ap(this, c0471x), j);
    }

    /* renamed from: a */
    private void m38a(Uri uri) {
        this.videoView = new AppLovinVideoView(this);
        this.videoView.setOnPreparedListener(new az(this));
        this.videoView.setOnCompletionListener(new bc(this));
        this.videoView.setOnErrorListener(new bd(this));
        this.videoView.setVideoURI(uri);
        this.videoView.setLayoutParams(new LayoutParams(-1, -1, 17));
        this.videoView.setOnTouchListener(new AppLovinTouchToClickListener(this, new bf(this)));
        this.f43w.addView(this.videoView);
        setContentView(this.f43w);
        m88q();
    }

    /* renamed from: a */
    private void m39a(View view, boolean z, long j) {
        float f = 1.0f;
        float f2 = z ? 0.0f : 1.0f;
        if (!z) {
            f = 0.0f;
        }
        Animation alphaAnimation = new AlphaAnimation(f2, f);
        alphaAnimation.setDuration(j);
        alphaAnimation.setAnimationListener(new av(this, view, z));
        view.startAnimation(alphaAnimation);
    }

    /* renamed from: a */
    private void m43a(ae aeVar) {
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f43w = new FrameLayout(this);
        this.f43w.setLayoutParams(layoutParams);
        this.f43w.setBackgroundColor(aeVar.m128E());
        this.f42v = new Handler();
        this.countdownManager = new C0494z(new Handler(), this.sdk);
    }

    /* renamed from: a */
    private void m44a(AppLovinAd appLovinAd) {
        AppLovinAdDisplayListener d = this.f22b.m360d();
        if (d != null) {
            d.adDisplayed(appLovinAd);
        }
        this.f26f = true;
    }

    /* renamed from: a */
    private void m45a(AppLovinAd appLovinAd, double d, boolean z) {
        this.f29i = true;
        AppLovinAdVideoPlaybackListener c = this.f22b.m359c();
        if (c != null) {
            c.videoPlaybackEnded(appLovinAd, d, z);
        }
    }

    /* renamed from: a */
    private void m46a(boolean z) {
        AppLovinSdkUtils.safePopulateImageView(this.f15C, z ? this.currentAd.m135L() : this.currentAd.m136M(), m33a(this.settingsProxy.m826B()));
    }

    /* renamed from: a */
    private boolean m47a() {
        int identifier = getResources().getIdentifier(this.settingsProxy.m846V(), "bool", "android");
        return identifier > 0 && getResources().getBoolean(identifier);
    }

    /* renamed from: b */
    private void m49b() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    /* renamed from: b */
    private void m50b(int i, boolean z) {
        int i2 = 0;
        int i3 = 1;
        boolean S = this.settingsProxy.m843S();
        if (this.f22b.m362f() == ag.ACTIVITY_PORTRAIT) {
            if (z) {
                if (i != 1 && i != 3) {
                    this.f23c = true;
                    setRequestedOrientation(1);
                } else if (!S) {
                } else {
                    if (i == 1) {
                        setRequestedOrientation(9);
                    } else {
                        setRequestedOrientation(1);
                    }
                }
            } else if (i != 0 && i != 2) {
                this.f23c = true;
                setRequestedOrientation(1);
            } else if (S) {
                if (i != 0) {
                    i3 = 9;
                }
                setRequestedOrientation(i3);
            }
        } else if (this.f22b.m362f() != ag.ACTIVITY_LANDSCAPE) {
        } else {
            if (z) {
                if (i != 0 && i != 2) {
                    this.f23c = true;
                    setRequestedOrientation(0);
                } else if (S) {
                    setRequestedOrientation(i == 2 ? 8 : 0);
                }
            } else if (i != 1 && i != 3) {
                this.f23c = true;
                setRequestedOrientation(0);
            } else if (S) {
                if (i != 1) {
                    i2 = 8;
                }
                setRequestedOrientation(i2);
            }
        }
    }

    /* renamed from: b */
    private void m52b(AppLovinAd appLovinAd) {
        dismiss();
        m57c(appLovinAd);
    }

    /* renamed from: b */
    private void m53b(boolean z) {
        this.videoMuted = z;
        MediaPlayer mediaPlayer = (MediaPlayer) this.f16D.get();
        if (mediaPlayer != null) {
            int i = z ? 0 : 1;
            mediaPlayer.setVolume((float) i, (float) i);
        }
    }

    /* renamed from: c */
    private void m57c(AppLovinAd appLovinAd) {
        if (!this.f27g) {
            this.f27g = true;
            if (this.f22b != null) {
                AppLovinAdDisplayListener d = this.f22b.m360d();
                if (d != null) {
                    d.adHidden(appLovinAd);
                }
            }
        }
    }

    /* renamed from: c */
    private boolean m58c() {
        return (this.f22b == null || this.settingsProxy == null || this.settingsProxy.m851a()) ? true : (this.settingsProxy.m853c() && this.f31k) ? true : this.settingsProxy.m852b() && this.poststitialWasDisplayed;
    }

    /* renamed from: d */
    private void m61d() {
        if (!this.currentAd.mo2137x() || this.currentAd.mo2136f() == null) {
            m65f();
            m67g();
            return;
        }
        this.sdk.getLogger().mo2288d("InterActivity", "Clicking through video...");
        clickThroughFromVideo();
    }

    /* renamed from: d */
    private void m62d(AppLovinAd appLovinAd) {
        if (!this.f28h) {
            this.f28h = true;
            AppLovinAdVideoPlaybackListener c = this.f22b.m359c();
            if (c != null) {
                c.videoPlaybackBegan(appLovinAd);
            }
        }
    }

    /* renamed from: e */
    private void m63e() {
        if (this.f24d.m889b() == -1) {
            this.f24d.m890b(System.currentTimeMillis() - this.f38r);
        }
    }

    /* renamed from: f */
    private void m65f() {
        if (this.settingsProxy.m875y() && this.f14B != null && this.f14B.getVisibility() != 8) {
            m39a(this.f14B, this.f14B.getVisibility() == 4, 750);
        }
    }

    /* renamed from: g */
    private void m67g() {
        ce v = this.currentAd.m155v();
        if (v != null && v.m382e() && !this.poststitialWasDisplayed && this.f18F != null) {
            m39a(this.f18F, this.f18F.getVisibility() == 4, v.m383f());
        }
    }

    /* renamed from: h */
    private void m69h() {
        Editor edit = m32M().edit();
        edit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        edit.putInt("com.applovin.interstitial.last_video_position", 0);
        edit.commit();
    }

    /* renamed from: i */
    private void m71i() {
        this.f30j = true;
        showPoststitial();
    }

    /* renamed from: j */
    private boolean m74j() {
        return m32M().getInt("com.applovin.interstitial.last_video_position", 0) > 0 ? this.videoMuted : this.settingsProxy.m832H() ? this.sdk.getSettings().isMuted() : this.settingsProxy.m830F();
    }

    /* renamed from: k */
    private void m75k() {
        int i = 3;
        this.f44x = C0471x.m365a(this.sdk, this, this.currentAd.m150q());
        this.f44x.setVisibility(8);
        this.f44x.setOnClickListener(new bg(this));
        int a = m33a(this.settingsProxy.m862l());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(a, a, (this.settingsProxy.m873w() ? 3 : 5) | 48);
        this.f44x.mo2181a(a);
        int a2 = m33a(this.settingsProxy.m864n());
        int a3 = m33a(this.settingsProxy.m866p());
        layoutParams.setMargins(a3, a2, a3, a2);
        this.f43w.addView(this.f44x, layoutParams);
        this.f46z = C0471x.m365a(this.sdk, this, this.currentAd.m151r());
        this.f46z.setVisibility(8);
        this.f46z.setOnClickListener(new bh(this));
        layoutParams = new LayoutParams(a, a, (this.settingsProxy.m872v() ? 3 : 5) | 48);
        layoutParams.setMargins(a3, a2, a3, a2);
        this.f46z.mo2181a(a);
        this.f43w.addView(this.f46z, layoutParams);
        this.f46z.bringToFront();
        if (m83o()) {
            int a4 = m33a(new dn(this.sdk).m867q());
            this.f45y = new View(this);
            this.f45y.setBackgroundColor(0);
            this.f45y.setVisibility(8);
            this.f13A = new View(this);
            this.f13A.setBackgroundColor(0);
            this.f13A.setVisibility(8);
            a += a4;
            int a5 = a2 - m33a(5);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(a, a, (this.settingsProxy.m873w() ? 3 : 5) | 48);
            layoutParams2.setMargins(a5, a5, a5, a5);
            if (!this.settingsProxy.m872v()) {
                i = 5;
            }
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(a, a, i | 48);
            layoutParams3.setMargins(a5, a5, a5, a5);
            this.f45y.setOnClickListener(new bi(this));
            this.f13A.setOnClickListener(new al(this));
            this.f43w.addView(this.f45y, layoutParams2);
            this.f45y.bringToFront();
            this.f43w.addView(this.f13A, layoutParams3);
            this.f13A.bringToFront();
        }
    }

    /* renamed from: l */
    private void m77l() {
        if (this.f15C == null) {
            try {
                this.videoMuted = m74j();
                this.f15C = new ImageView(this);
                if (m80m()) {
                    this.sdk.getLogger().mo2288d("InterActivity", "Mute button should be hidden");
                    return;
                }
                int a = m33a(this.settingsProxy.m826B());
                ViewGroup.LayoutParams layoutParams = new LayoutParams(a, a, this.settingsProxy.m827C());
                this.f15C.setScaleType(ScaleType.FIT_CENTER);
                a = m33a(this.settingsProxy.m828D());
                layoutParams.setMargins(a, a, a, a);
                Object L = this.videoMuted ? this.currentAd.m135L() : this.currentAd.m136M();
                if (L != null) {
                    this.sdk.getLogger().mo2288d("InterActivity", "Added mute button with params: " + layoutParams);
                    m46a(this.videoMuted);
                    this.f15C.setClickable(true);
                    this.f15C.setOnClickListener(new am(this));
                    this.f43w.addView(this.f15C, layoutParams);
                    this.f15C.bringToFront();
                    return;
                }
                this.sdk.getLogger().mo2289e("InterActivity", "Attempting to add mute button but could not find uri = " + L);
            } catch (Throwable e) {
                this.sdk.getLogger().mo2295w("InterActivity", "Failed to attach mute button", e);
            }
        }
    }

    /* renamed from: m */
    private boolean m80m() {
        return !this.settingsProxy.m876z() ? true : this.settingsProxy.m825A() ? m74j() ? false : !this.settingsProxy.m831G() : false;
    }

    /* renamed from: n */
    private void m82n() {
        runOnUiThread(new an(this));
    }

    /* renamed from: o */
    private boolean m83o() {
        return this.settingsProxy.m867q() > 0;
    }

    /* renamed from: p */
    private void m85p() {
        runOnUiThread(new ao(this));
    }

    /* renamed from: q */
    private void m88q() {
        if (this.currentAd.m148o() >= 0.0f) {
            C0471x c0471x = (!this.f33m || this.f46z == null) ? this.f44x : this.f46z;
            m37a(fk.m1092c(this.currentAd.m148o()), c0471x);
        }
    }

    /* renamed from: r */
    private void m89r() {
        int i = (!this.settingsProxy.m858h() || m95u() <= 0) ? 0 : 1;
        if (this.f14B == null && i != 0) {
            this.f14B = new C0491v(this);
            i = this.currentAd.m127D();
            this.f14B.m418c(i);
            this.f14B.m415b((float) this.settingsProxy.m857g());
            this.f14B.m420d(i);
            this.f14B.m412a((float) this.settingsProxy.m856f());
            this.f14B.m416b(m95u());
            this.f14B.m413a(m95u());
            ViewGroup.LayoutParams layoutParams = new LayoutParams(m33a(this.settingsProxy.m855e()), m33a(this.settingsProxy.m855e()), this.settingsProxy.m871u());
            int a = m33a(this.settingsProxy.m870t());
            layoutParams.setMargins(a, a, a, a);
            this.f43w.addView(this.f14B, layoutParams);
            this.f14B.bringToFront();
            this.f14B.setVisibility(0);
            this.countdownManager.m438a("COUNTDOWN_CLOCK", 1000, new aq(this, m93t()));
        }
    }

    /* renamed from: s */
    private boolean m92s() {
        return (this.f36p || this.poststitialWasDisplayed || !this.videoView.isPlaying()) ? false : true;
    }

    /* renamed from: t */
    private long m93t() {
        return TimeUnit.SECONDS.toMillis((long) m95u());
    }

    /* renamed from: u */
    private int m95u() {
        int C = this.currentAd.m126C();
        return (C <= 0 && this.settingsProxy.m869s()) ? this.computedLengthSeconds + 1 : C;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: v */
    private void m98v() {
        if (this.f19G == null && this.currentAd.m132I()) {
            this.logger.mo2291i("InterActivity", "Attaching video progress bar...");
            this.f19G = new ProgressBar(this, null, 16842872);
            this.f19G.setMax(this.settingsProxy.am());
            this.f19G.setPadding(0, 0, 0, 0);
            if (C0516u.m1166e()) {
                try {
                    this.f19G.setProgressTintList(ColorStateList.valueOf(this.currentAd.m133J()));
                } catch (Throwable th) {
                    this.logger.mo2290e("InterActivity", "Unable to update progress bar color.", th);
                }
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.videoView.getWidth(), 20, 80);
            layoutParams.setMargins(0, 0, 0, this.settingsProxy.an());
            this.f43w.addView(this.f19G, layoutParams);
            this.f19G.bringToFront();
            this.countdownManager.m438a("PROGRESS_BAR", this.settingsProxy.al(), new ar(this));
        }
    }

    /* renamed from: w */
    private void m99w() {
        ce v = this.currentAd.m155v();
        if (AppLovinSdkUtils.isValidString(this.currentAd.m154u()) && v != null && this.f18F == null) {
            this.logger.mo2291i("InterActivity", "Attaching video button...");
            this.f18F = m102x();
            double b = ((double) v.m379b()) / 100.0d;
            ViewGroup.LayoutParams layoutParams = new LayoutParams((int) ((((double) v.m378a()) / 100.0d) * ((double) this.videoView.getWidth())), (int) (((double) this.videoView.getHeight()) * b), v.m381d());
            int a = m33a(v.m380c());
            layoutParams.setMargins(a, a, a, a);
            this.f43w.addView(this.f18F, layoutParams);
            this.f18F.bringToFront();
            if (v.m386i() > 0.0f) {
                this.f18F.setVisibility(4);
                this.f42v.postDelayed(new as(this, v), fk.m1092c(v.m386i()));
            }
            if (v.m387j() > 0.0f) {
                this.f42v.postDelayed(new at(this, v), fk.m1092c(v.m387j()));
            }
        }
    }

    /* renamed from: x */
    private cf m102x() {
        this.logger.mo2288d("InterActivity", "Create video button with HTML = " + this.currentAd.m154u());
        cg cgVar = new cg(this.sdk);
        this.f20H = new au(this);
        cgVar.m390a(new WeakReference(this.f20H));
        cf cfVar = new cf(cgVar, getApplicationContext());
        cfVar.m388a(this.currentAd.m154u());
        return cfVar;
    }

    /* renamed from: y */
    private void m104y() {
        if (m18A()) {
            m28I();
            this.logger.mo2288d("InterActivity", "Prompting incentivized ad close warning");
            this.f17E.m607b();
            return;
        }
        skipVideo();
    }

    /* renamed from: z */
    private void m105z() {
        if (m20B()) {
            this.logger.mo2288d("InterActivity", "Prompting incentivized non-video ad close warning");
            this.f17E.m608c();
            return;
        }
        dismiss();
    }

    public void clickThroughFromVideo() {
        try {
            m63e();
            ((AppLovinAdServiceImpl) this.sdk.getAdService()).trackAndLaunchVideoClick(this.currentAd, this.currentPlacement, this.f21a, this.currentAd.mo2136f());
            AppLovinAdClickListener e = this.f22b.m361e();
            if (e != null) {
                e.adClicked(this.currentAd);
            }
        } catch (Throwable th) {
            this.sdk.getLogger().mo2290e("InterActivity", "Encountered error while clicking through video.", th);
        }
    }

    public void continueVideo() {
        m30K();
    }

    public void dismiss() {
        long currentTimeMillis = System.currentTimeMillis() - this.f38r;
        this.f24d.m887a(currentTimeMillis);
        this.logger.mo2291i("InterActivity", "Dismissing ad after " + currentTimeMillis + " milliseconds elapsed");
        ((AdViewControllerImpl) this.f21a.getAdViewController()).setIsForegroundClickInvalidated(true);
        m69h();
        m31L();
        if (this.f22b != null) {
            if (this.currentAd != null) {
                m57c(this.currentAd);
            }
            this.f22b.m357a(false);
            this.f22b.m364h();
        }
        finish();
    }

    public void exitWithError(String str) {
        try {
            Log.e("InterActivity", "Failed to properly render an Interstitial Activity, due to error: " + str, new Throwable("Initialized = " + bt.f279d + "; CleanedUp = " + bt.f280e));
            m57c(new ah());
        } catch (Throwable e) {
            Log.e("InterActivity", "Failed to show a video ad due to error:", e);
        }
        finish();
    }

    public boolean getPoststitialWasDisplayed() {
        return this.poststitialWasDisplayed;
    }

    public int getVideoPercentViewed() {
        if (this.f30j) {
            return 100;
        }
        if (this.videoView != null) {
            int duration = this.videoView.getDuration();
            return duration > 0 ? (int) ((((double) this.videoView.getCurrentPosition()) / ((double) duration)) * 100.0d) : this.f40t;
        } else {
            this.logger.mo2289e("InterActivity", "No video view detected on video end");
            return 0;
        }
    }

    public void handleMediaError() {
        if (this.f41u.compareAndSet(false, true)) {
            if (this.settingsProxy.m860j()) {
                this.logger.mo2289e("InterActivity", "Handling media player error - Finishing activity...");
                finish();
            } else {
                this.logger.mo2289e("InterActivity", "Handling media player error - Showing poststitial...");
                showPoststitial();
            }
            this.logger.mo2289e("InterActivity", "Finished handling media player error.");
            return;
        }
        this.logger.mo2289e("InterActivity", "Already handled media player error. Doing nothing...");
    }

    protected boolean isFullyWatched() {
        return getVideoPercentViewed() >= 95;
    }

    public void onBackPressed() {
        if (m58c()) {
            this.logger.mo2288d("InterActivity", "Back button was pressed; forwarding to Android for handling...");
            super.onBackPressed();
            return;
        }
        try {
            if (this.f33m && this.f46z != null && this.f46z.getVisibility() == 0 && this.f46z.getAlpha() > 0.0f && !this.f31k) {
                this.logger.mo2288d("InterActivity", "Back button was pressed; forwarding as a click to skip button.");
                this.f46z.performClick();
            } else if (this.f44x == null || this.f44x.getVisibility() != 0 || this.f44x.getAlpha() <= 0.0f) {
                this.logger.mo2288d("InterActivity", "Back button was pressed, but was not eligible for dismissal.");
            } else {
                this.logger.mo2288d("InterActivity", "Back button was pressed; forwarding as a click to close button.");
                this.f44x.performClick();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        requestWindowFeature(1);
        String stringExtra = getIntent().getStringExtra(KEY_WRAPPER_ID);
        if (stringExtra == null || stringExtra.isEmpty()) {
            exitWithError("Wrapper ID is null");
        } else {
            this.f22b = bt.m339a(stringExtra);
            if (this.f22b == null && lastKnownWrapper != null) {
                this.f22b = lastKnownWrapper;
            }
            if (this.f22b != null) {
                AppLovinAd b = this.f22b.m358b();
                this.sdk = (AppLovinSdkImpl) this.f22b.m354a();
                this.logger = this.f22b.m354a().getLogger();
                this.settingsProxy = new dn(this.f22b.m354a());
                this.f24d = new dp(this.sdk);
                this.currentPlacement = this.f22b.m363g();
                if (b != null) {
                    ae aeVar = (ae) b;
                    View findViewById = findViewById(16908290);
                    if (findViewById != null) {
                        if (aeVar.isVideoAd()) {
                            findViewById.setBackgroundColor(aeVar.m128E());
                        } else {
                            findViewById.setBackgroundColor(aeVar.m129F());
                        }
                    }
                    this.f38r = System.currentTimeMillis();
                    this.f24d.m888a(b);
                    this.f24d.m890b(-1);
                    if (aeVar.m158y()) {
                        getWindow().setFlags(16777216, 16777216);
                    }
                    Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
                    int a = m35a(defaultDisplay);
                    int rotation = defaultDisplay.getRotation();
                    boolean z2 = (a == 2 && rotation == 0) || ((a == 2 && rotation == 2) || ((a == 1 && rotation == 1) || (a == 1 && rotation == 3)));
                    if (aeVar.m125B()) {
                        int a2 = m34a(rotation, z2);
                        if (a2 != -1) {
                            this.logger.mo2288d("InterActivity", "Locking activity orientation to current orientation: " + a2);
                            setRequestedOrientation(a2);
                        } else {
                            this.logger.mo2289e("InterActivity", "Unable to detect current orientation. Locking to targeted orientation...");
                            m50b(rotation, z2);
                        }
                    } else {
                        this.logger.mo2288d("InterActivity", "Locking activity orientation to targeted orientation...");
                        m50b(rotation, z2);
                    }
                    this.f21a = new AppLovinAdView(this.sdk, AppLovinAdSize.INTERSTITIAL, (Activity) this);
                    this.f21a.setAutoDestroy(false);
                    this.f22b.m355a((ad) this);
                    this.f33m = this.settingsProxy.m868r();
                    if (!(C0516u.m1158a(getApplicationContext()) || C0516u.m1158a(getApplicationContext()))) {
                        z = false;
                    }
                    this.f37q = z;
                    this.f17E = new bl(this.sdk, this);
                } else {
                    exitWithError("No current ad found.");
                }
            } else {
                exitWithError("Wrapper is null; initialized state: " + Boolean.toString(bt.f279d));
            }
        }
        Editor edit = m32M().edit();
        edit.putBoolean("com.applovin.interstitial.should_resume_video", false);
        edit.commit();
        m69h();
        m26G();
    }

    protected void onDestroy() {
        try {
            if (this.f21a != null) {
                ViewParent parent = this.f21a.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.f21a);
                }
                this.f21a.destroy();
                this.f21a = null;
            }
            if (this.videoView != null) {
                this.videoView.pause();
                this.videoView.stopPlayback();
            }
            if (this.countdownManager != null) {
                this.countdownManager.m439b();
            }
            if (this.currentAd != null) {
                m31L();
                m57c(this.currentAd);
            }
        } catch (Throwable th) {
            if (this.currentAd != null) {
                m31L();
                m57c(this.currentAd);
            }
        }
        super.onDestroy();
    }

    protected void onPause() {
        this.logger.mo2288d("InterActivity", "App paused...");
        this.f39s = System.currentTimeMillis();
        if (!this.f25e) {
            if (this.f37q) {
                m28I();
            } else if (!this.f23c) {
                m28I();
            }
        }
        this.f22b.m357a(false);
        this.f17E.m606a();
        super.onPause();
    }

    protected void onResume() {
        boolean z = false;
        super.onResume();
        this.logger.mo2288d("InterActivity", "App resumed...");
        this.f22b.m357a(true);
        if (!this.f35o) {
            this.f24d.m891c(System.currentTimeMillis() - this.f39s);
            if (!m32M().getBoolean("com.applovin.interstitial.should_resume_video", false) || this.f17E.m609d() || this.poststitialWasDisplayed) {
                if (this.currentAd instanceof C0503g) {
                    z = ((C0503g) this.currentAd).m1127h();
                }
                if (this.settingsProxy.m859i() && !this.currentAd.m159z() && this.poststitialWasDisplayed && this.f44x != null && !r0) {
                    m37a(0, this.f44x);
                    return;
                }
                return;
            }
            m29J();
            if (this.settingsProxy.m859i() && !this.currentAd.m124A() && !this.poststitialWasDisplayed && this.f33m && this.f46z != null) {
                m37a(0, this.f46z);
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.logger.mo2288d("InterActivity", "Window gained focus");
            try {
                if (C0516u.m1165d() && this.settingsProxy.m837M() && m47a()) {
                    m49b();
                    if (this.settingsProxy.m847W() > 0) {
                        this.f42v.postDelayed(new ak(this), this.settingsProxy.m847W());
                    }
                } else {
                    getWindow().setFlags(1024, 1024);
                }
                if (this.settingsProxy.m845U() && !this.poststitialWasDisplayed) {
                    m29J();
                }
            } catch (Throwable th) {
                this.logger.mo2290e("InterActivity", "Setting window flags failed.", th);
            }
        } else {
            this.logger.mo2288d("InterActivity", "Window lost focus");
            if (this.settingsProxy.m845U() && !this.poststitialWasDisplayed) {
                m28I();
            }
        }
        this.f35o = false;
    }

    protected void playVideo() {
        m62d(this.currentAd);
        this.videoView.start();
        this.countdownManager.m437a();
    }

    protected boolean shouldContinueFullLengthVideoCountdown() {
        return (this.f30j || this.poststitialWasDisplayed) ? false : true;
    }

    public void showPoststitial() {
        try {
            if (this.videoView != null) {
                this.f40t = getVideoPercentViewed();
                this.videoView.stopPlayback();
            }
            if (this.f21a != null) {
                ViewParent parent = this.f21a.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(this.f21a);
                }
                View frameLayout = new FrameLayout(this);
                frameLayout.setLayoutParams(new LayoutParams(-1, -1));
                frameLayout.setBackgroundColor(this.currentAd.m129F());
                frameLayout.addView(this.f21a);
                if (this.f43w != null) {
                    this.f43w.removeAllViewsInLayout();
                }
                if (m83o() && this.f45y != null) {
                    frameLayout.addView(this.f45y);
                    this.f45y.bringToFront();
                }
                if (this.f44x != null) {
                    parent = this.f44x.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(this.f44x);
                    }
                    frameLayout.addView(this.f44x);
                    this.f44x.bringToFront();
                }
                setContentView(frameLayout);
                if (this.settingsProxy.m841Q()) {
                    this.f21a.setVisibility(4);
                    this.f21a.setVisibility(0);
                }
            }
            if (this.currentAd instanceof C0503g ? ((C0503g) this.currentAd).m1127h() : false) {
                this.logger.mo2288d("InterActivity", "Skip showing of close button");
            } else if (this.currentAd.m149p() >= 0.0f) {
                m37a(fk.m1092c(this.currentAd.m149p()), this.f44x);
            } else if (this.currentAd.m149p() == -2.0f) {
                this.f44x.setVisibility(0);
            } else {
                m37a(0, this.f44x);
            }
            this.poststitialWasDisplayed = true;
        } catch (Throwable th) {
            this.logger.mo2290e("InterActivity", "Encountered error while showing poststitial. Dismissing...", th);
            dismiss();
        }
    }

    public void skipVideo() {
        if (this.currentAd.m153t()) {
            dismiss();
        } else {
            showPoststitial();
        }
    }

    public void toggleMute() {
        boolean z = !m27H();
        try {
            m53b(z);
            m46a(z);
        } catch (Throwable th) {
            this.logger.mo2290e("InterActivity", "Unable to set volume to " + z, th);
        }
    }
}
