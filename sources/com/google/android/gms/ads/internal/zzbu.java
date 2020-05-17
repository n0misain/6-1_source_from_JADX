package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzahq;
import com.google.android.gms.internal.zzaix;
import com.google.android.gms.internal.zzaka;
import java.util.ArrayList;
import java.util.List;

public final class zzbu extends ViewSwitcher {
    private final zzahq zzwB;
    @Nullable
    private final zzaix zzwC;
    private boolean zzwD = true;

    public zzbu(Context context, String str, String str2, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        super(context);
        this.zzwB = new zzahq(context);
        this.zzwB.setAdUnitId(str);
        this.zzwB.zzaO(str2);
        if (context instanceof Activity) {
            this.zzwC = new zzaix((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
        } else {
            this.zzwC = new zzaix(null, this, onGlobalLayoutListener, onScrollChangedListener);
        }
        this.zzwC.zzig();
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.zzwC != null) {
            this.zzwC.onAttachedToWindow();
        }
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.zzwC != null) {
            this.zzwC.onDetachedFromWindow();
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zzwD) {
            this.zzwB.zzf(motionEvent);
        }
        return false;
    }

    public final void removeAllViews() {
        int i;
        int i2 = 0;
        List arrayList = new ArrayList();
        for (i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null && (childAt instanceof zzaka)) {
                arrayList.add((zzaka) childAt);
            }
        }
        super.removeAllViews();
        ArrayList arrayList2 = (ArrayList) arrayList;
        i = arrayList2.size();
        while (i2 < i) {
            Object obj = arrayList2.get(i2);
            i2++;
            ((zzaka) obj).destroy();
        }
    }

    public final zzahq zzcf() {
        return this.zzwB;
    }

    public final void zzcg() {
        zzafr.m1217v("Disable position monitoring on adFrame.");
        if (this.zzwC != null) {
            this.zzwC.zzih();
        }
    }

    public final void zzch() {
        zzafr.m1217v("Enable debug gesture detector on adFrame.");
        this.zzwD = true;
    }

    public final void zzci() {
        zzafr.m1217v("Disable debug gesture detector on adFrame.");
        this.zzwD = false;
    }
}
