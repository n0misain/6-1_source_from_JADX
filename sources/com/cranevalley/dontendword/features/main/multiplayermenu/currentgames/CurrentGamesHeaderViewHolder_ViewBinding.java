package com.cranevalley.dontendword.features.main.multiplayermenu.currentgames;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class CurrentGamesHeaderViewHolder_ViewBinding<T extends CurrentGamesHeaderViewHolder> implements Unbinder {
    protected T target;

    @UiThread
    public CurrentGamesHeaderViewHolder_ViewBinding(T target, View source) {
        this.target = target;
        target.titleTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.titleTextView, "field 'titleTextView'", AppCompatTextView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.titleTextView = null;
        this.target = null;
    }
}
