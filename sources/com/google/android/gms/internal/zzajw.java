package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.zzbs;
import java.lang.ref.WeakReference;

@zzzn
final class zzajw extends zzajy implements OnGlobalLayoutListener {
    private final WeakReference<OnGlobalLayoutListener> zzabk;

    public zzajw(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.zzabk = new WeakReference(onGlobalLayoutListener);
    }

    public final void onGlobalLayout() {
        OnGlobalLayoutListener onGlobalLayoutListener = (OnGlobalLayoutListener) this.zzabk.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            detach();
        }
    }

    protected final void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    protected final void zzb(ViewTreeObserver viewTreeObserver) {
        zzbs.zzbB().zza(viewTreeObserver, (OnGlobalLayoutListener) this);
    }
}
