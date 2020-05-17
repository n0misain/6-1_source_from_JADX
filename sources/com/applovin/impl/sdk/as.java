package com.applovin.impl.sdk;

class as implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f526a;
    /* renamed from: b */
    final /* synthetic */ aq f527b;

    as(aq aqVar, int i) {
        this.f527b = aqVar;
        this.f526a = i;
    }

    public void run() {
        this.f527b.f523b.failedToReceiveAd(this.f526a);
    }
}
