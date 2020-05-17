package com.cranevalley.dontendword.features.main.multiplayermenu.currentgames;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class CurrentGamesFragment_ViewBinding<T extends CurrentGamesFragment> implements Unbinder {
    protected T target;

    @UiThread
    public CurrentGamesFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.currentGamesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0521R.id.currentGamesRecyclerView, "field 'currentGamesRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.currentGamesRecyclerView = null;
        this.target = null;
    }
}
