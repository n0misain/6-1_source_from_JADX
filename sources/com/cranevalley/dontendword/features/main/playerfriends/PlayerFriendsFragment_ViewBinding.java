package com.cranevalley.dontendword.features.main.playerfriends;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class PlayerFriendsFragment_ViewBinding<T extends PlayerFriendsFragment> implements Unbinder {
    protected T target;
    private View view2131820838;

    @UiThread
    public PlayerFriendsFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.playerFriendsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0521R.id.playerFriendsRecyclerView, "field 'playerFriendsRecyclerView'", RecyclerView.class);
        target.emptyNoteLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0521R.id.emptyNoteLayout, "field 'emptyNoteLayout'", LinearLayout.class);
        View view = Utils.findRequiredView(source, C0521R.id.addFriendsButton, "method 'onClickAddFriends'");
        this.view2131820838 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickAddFriends(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.playerFriendsRecyclerView = null;
        target.emptyNoteLayout = null;
        this.view2131820838.setOnClickListener(null);
        this.view2131820838 = null;
        this.target = null;
    }
}
