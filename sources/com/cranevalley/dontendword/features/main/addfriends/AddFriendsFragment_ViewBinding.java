package com.cranevalley.dontendword.features.main.addfriends;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class AddFriendsFragment_ViewBinding<T extends AddFriendsFragment> implements Unbinder {
    protected T target;
    private View view2131820693;
    private View view2131820694;

    @UiThread
    public AddFriendsFragment_ViewBinding(final T target, View source) {
        this.target = target;
        View view = Utils.findRequiredView(source, C0521R.id.searchUsersEditText, "field 'searchUsersEditText' and method 'onEditorActionSearchUsers'");
        target.searchUsersEditText = (AppCompatEditText) Utils.castView(view, C0521R.id.searchUsersEditText, "field 'searchUsersEditText'", AppCompatEditText.class);
        this.view2131820693 = view;
        ((TextView) view).setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView p0, int p1, KeyEvent p2) {
                return target.onEditorActionSearchUsers(p0, p1, p2);
            }
        });
        target.addFriendsRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0521R.id.addFriendsRecyclerView, "field 'addFriendsRecyclerView'", RecyclerView.class);
        target.emptyNoteLayout = (LinearLayout) Utils.findRequiredViewAsType(source, C0521R.id.emptyNoteLayout, "field 'emptyNoteLayout'", LinearLayout.class);
        view = Utils.findRequiredView(source, C0521R.id.searchUsersButton, "method 'onClickSearchUsers'");
        this.view2131820694 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickSearchUsers(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.searchUsersEditText = null;
        target.addFriendsRecyclerView = null;
        target.emptyNoteLayout = null;
        ((TextView) this.view2131820693).setOnEditorActionListener(null);
        this.view2131820693 = null;
        this.view2131820694.setOnClickListener(null);
        this.view2131820694 = null;
        this.target = null;
    }
}
