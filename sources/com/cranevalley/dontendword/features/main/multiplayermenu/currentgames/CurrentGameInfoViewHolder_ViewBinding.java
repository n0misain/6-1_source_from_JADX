package com.cranevalley.dontendword.features.main.multiplayermenu.currentgames;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;

public class CurrentGameInfoViewHolder_ViewBinding<T extends CurrentGameInfoViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public CurrentGameInfoViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.photoImageView = (RoundedImageView) Utils.findRequiredViewAsType(source, C0521R.id.photoImageView, "field 'photoImageView'", RoundedImageView.class);
        target.displayNameTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.displayNameTextView, "field 'displayNameTextView'", AppCompatTextView.class);
        target.lettersTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.lettersTextView, "field 'lettersTextView'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.photoImageView = null;
        target.displayNameTextView = null;
        target.lettersTextView = null;
        this.target = null;
    }
}
