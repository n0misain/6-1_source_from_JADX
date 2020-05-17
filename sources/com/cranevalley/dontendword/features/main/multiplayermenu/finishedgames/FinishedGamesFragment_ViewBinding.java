package com.cranevalley.dontendword.features.main.multiplayermenu.finishedgames;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class FinishedGamesFragment_ViewBinding<T extends FinishedGamesFragment> implements Unbinder {
    protected T target;

    @UiThread
    public FinishedGamesFragment_ViewBinding(T target, View source) {
        this.target = target;
        target.finishedGamesRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0521R.id.recyclerViewFinishedGames, "field 'finishedGamesRecyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.finishedGamesRecyclerView = null;
        this.target = null;
    }
}
