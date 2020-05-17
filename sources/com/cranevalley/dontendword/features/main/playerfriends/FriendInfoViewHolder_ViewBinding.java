package com.cranevalley.dontendword.features.main.playerfriends;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;

public class FriendInfoViewHolder_ViewBinding<T extends FriendInfoViewHolder> implements Unbinder {
    protected T target;
    private View view2131820747;
    private View view2131820748;
    private View view2131820749;

    @UiThread
    public FriendInfoViewHolder_ViewBinding(final T target, View source) {
        this.target = target;
        target.photoImageView = (RoundedImageView) Utils.findRequiredViewAsType(source, C0521R.id.photoImageView, "field 'photoImageView'", RoundedImageView.class);
        target.displayNameTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.displayNameTextView, "field 'displayNameTextView'", AppCompatTextView.class);
        View view = Utils.findRequiredView(source, C0521R.id.chatButton, "field 'chatButton' and method 'onClickChat'");
        target.chatButton = (AppCompatButton) Utils.castView(view, C0521R.id.chatButton, "field 'chatButton'", AppCompatButton.class);
        this.view2131820748 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickChat(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.playButton, "method 'onClickPlay'");
        this.view2131820749 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickPlay(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.unfriendButton, "method 'onClickUnfriend'");
        this.view2131820747 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickUnfriend(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.photoImageView = null;
        target.displayNameTextView = null;
        target.chatButton = null;
        this.view2131820748.setOnClickListener(null);
        this.view2131820748 = null;
        this.view2131820749.setOnClickListener(null);
        this.view2131820749 = null;
        this.view2131820747.setOnClickListener(null);
        this.view2131820747 = null;
        this.target = null;
    }
}
