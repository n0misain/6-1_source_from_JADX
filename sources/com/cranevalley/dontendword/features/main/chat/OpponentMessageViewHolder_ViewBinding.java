package com.cranevalley.dontendword.features.main.chat;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;

public class OpponentMessageViewHolder_ViewBinding<T extends OpponentMessageViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public OpponentMessageViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.photoImageView = (RoundedImageView) Utils.findRequiredViewAsType(source, C0521R.id.photoImageView, "field 'photoImageView'", RoundedImageView.class);
        target.textTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.textTextView, "field 'textTextView'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.photoImageView = null;
        target.textTextView = null;
        this.target = null;
    }
}
