package com.cranevalley.dontendword.features.main.addfriends;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;

public class AddFriendInfoViewHolder_ViewBinding<T extends AddFriendInfoViewHolder> implements Unbinder {
    protected T target;
    private View view2131820690;

    @UiThread
    public AddFriendInfoViewHolder_ViewBinding(final T target, View source) {
        this.target = target;
        target.photoImageView = (RoundedImageView) Utils.findRequiredViewAsType(source, C0521R.id.photoImageView, "field 'photoImageView'", RoundedImageView.class);
        target.friendIndicatorImageView = (AppCompatImageView) Utils.findRequiredViewAsType(source, C0521R.id.friendIndicatorImageView, "field 'friendIndicatorImageView'", AppCompatImageView.class);
        target.displayNameTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.displayNameTextView, "field 'displayNameTextView'", AppCompatTextView.class);
        View view = Utils.findRequiredView(source, C0521R.id.addFriendButton, "field 'addFriendButton' and method 'onClickAddFriend'");
        target.addFriendButton = (AppCompatImageButton) Utils.castView(view, C0521R.id.addFriendButton, "field 'addFriendButton'", AppCompatImageButton.class);
        this.view2131820690 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickAddFriend(p0);
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
        target.friendIndicatorImageView = null;
        target.displayNameTextView = null;
        target.addFriendButton = null;
        this.view2131820690.setOnClickListener(null);
        this.view2131820690 = null;
        this.target = null;
    }
}
