package com.google.android.gms.internal;

import java.util.Iterator;

final class ny implements Iterable<oa> {
    private final int length;
    private long value;

    public ny(int i) {
        int i2 = i + 1;
        this.length = (int) Math.floor(Math.log((double) i2) / Math.log(2.0d));
        this.value = ((long) i2) & (((long) Math.pow(2.0d, (double) this.length)) - 1);
    }

    public final Iterator<oa> iterator() {
        return new nz(this);
    }
}
