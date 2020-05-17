package com.google.android.gms.internal;

import android.webkit.ConsoleMessage.MessageLevel;

final /* synthetic */ class zzald {
    static final /* synthetic */ int[] zzacK = new int[MessageLevel.values().length];

    static {
        try {
            zzacK[MessageLevel.ERROR.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzacK[MessageLevel.WARNING.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzacK[MessageLevel.LOG.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            zzacK[MessageLevel.TIP.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zzacK[MessageLevel.DEBUG.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
