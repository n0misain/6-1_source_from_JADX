package com.applovin.impl.adview;

import android.os.Handler;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.applovin.impl.adview.z */
public final class C0494z {
    /* renamed from: a */
    private final AppLovinLogger f394a;
    /* renamed from: b */
    private final Handler f395b;
    /* renamed from: c */
    private final Set f396c = new HashSet();
    /* renamed from: d */
    private final AtomicInteger f397d = new AtomicInteger();

    public C0494z(Handler handler, AppLovinSdk appLovinSdk) {
        if (handler == null) {
            throw new IllegalArgumentException("Unable to create CountdownManager. No countdownHandler specified.");
        } else if (appLovinSdk == null) {
            throw new IllegalArgumentException("Unable to create CountdownManager. No sdk specified.");
        } else {
            this.f395b = handler;
            this.f394a = appLovinSdk.getLogger();
        }
    }

    /* renamed from: a */
    private void m434a(ac acVar, int i) {
        this.f395b.postDelayed(new aa(this, acVar, i), acVar.m290b());
    }

    /* renamed from: a */
    public void m437a() {
        Set<ac> hashSet = new HashSet(this.f396c);
        this.f394a.mo2288d("CountdownManager", "Starting " + hashSet.size() + " countdowns...");
        int incrementAndGet = this.f397d.incrementAndGet();
        for (ac acVar : hashSet) {
            this.f394a.mo2288d("CountdownManager", "Starting countdown: " + acVar.m288a() + " for generation " + incrementAndGet + "...");
            m434a(acVar, incrementAndGet);
        }
    }

    /* renamed from: a */
    public void m438a(String str, long j, ab abVar) {
        if (j <= 0) {
            throw new IllegalArgumentException("Unable to add countdown. Invalid countdown step specified.");
        } else if (this.f395b == null) {
            throw new IllegalArgumentException("Unable to add countdown. No handler specified.");
        } else {
            this.f394a.mo2288d("CountdownManager", "Adding countdown: " + str);
            this.f396c.add(new ac(str, j, abVar));
        }
    }

    /* renamed from: b */
    public void m439b() {
        this.f394a.mo2288d("CountdownManager", "Removing all countdowns...");
        m440c();
        this.f396c.clear();
    }

    /* renamed from: c */
    public void m440c() {
        this.f394a.mo2288d("CountdownManager", "Stopping countdowns...");
        this.f397d.incrementAndGet();
        this.f395b.removeCallbacksAndMessages(null);
    }
}
