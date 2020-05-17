package com.google.android.gms.internal;

import java.util.Random;

public final class zc {
    private static long zzcjA = 0;
    private static final int[] zzcjB = new int[12];
    private static final Random zzcjz = new Random();

    private static void zzJH() {
        int i = 11;
        while (i >= 0) {
            if (zzcjB[i] != 63) {
                zzcjB[i] = zzcjB[i] + 1;
                return;
            } else {
                zzcjB[i] = 0;
                i--;
            }
        }
    }

    public static synchronized String zzaz(long j) {
        String stringBuilder;
        int i = 0;
        synchronized (zc.class) {
            int i2;
            int i3 = j == zzcjA ? 1 : 0;
            zzcjA = j;
            char[] cArr = new char[8];
            StringBuilder stringBuilder2 = new StringBuilder(20);
            for (i2 = 7; i2 >= 0; i2--) {
                cArr[i2] = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".charAt((int) (j % 64));
                j /= 64;
            }
            stringBuilder2.append(cArr);
            if (i3 == 0) {
                for (i2 = 0; i2 < 12; i2++) {
                    zzcjB[i2] = zzcjz.nextInt(64);
                }
            } else {
                zzJH();
            }
            while (i < 12) {
                stringBuilder2.append("-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".charAt(zzcjB[i]));
                i++;
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }
}
