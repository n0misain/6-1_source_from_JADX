package com.cranevalley.dontendword.features.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
    protected T target;

    @UiThread
    public MainActivity_ViewBinding(T target, View source) {
        this.target = target;
        target.bannerLayout = (FrameLayout) Utils.findRequiredViewAsType(source, C0521R.id.bannerLayout, "field 'bannerLayout'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.bannerLayout = null;
        this.target = null;
    }
}
