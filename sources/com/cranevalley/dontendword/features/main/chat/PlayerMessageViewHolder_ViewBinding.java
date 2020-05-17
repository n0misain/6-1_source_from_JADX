package com.cranevalley.dontendword.features.main.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class PlayerMessageViewHolder_ViewBinding<T extends PlayerMessageViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public PlayerMessageViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.textTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.textTextView, "field 'textTextView'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.textTextView = null;
        this.target = null;
    }
}
