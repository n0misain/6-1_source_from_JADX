package com.cranevalley.dontendword.features.main.playerprofile;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;
import com.makeramen.roundedimageview.RoundedImageView;

public class PlayerProfileFragment_ViewBinding<T extends PlayerProfileFragment> implements Unbinder {
    protected T target;
    private View view2131820839;
    private View view2131820845;
    private View view2131820847;
    private View view2131820849;

    @UiThread
    public PlayerProfileFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.displayNameTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.displayNameTextView, "field 'displayNameTextView'", AppCompatTextView.class);
        target.gameCountTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.gameCountTextView, "field 'gameCountTextView'", AppCompatTextView.class);
        target.winCountTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.winCountTextView, "field 'winCountTextView'", AppCompatTextView.class);
        target.loseCountTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.loseCountTextView, "field 'loseCountTextView'", AppCompatTextView.class);
        target.drawCountTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.drawCountTextView, "field 'drawCountTextView'", AppCompatTextView.class);
        target.ongoingCountTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.ongoingCountTextView, "field 'ongoingCountTextView'", AppCompatTextView.class);
        target.facebookConnectIndicatorTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.facebookConnectIndicatorTextView, "field 'facebookConnectIndicatorTextView'", AppCompatTextView.class);
        target.twitterConnectIndicatorTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.twitterConnectIndicatorTextView, "field 'twitterConnectIndicatorTextView'", AppCompatTextView.class);
        target.googleConnectIndicatorTextView = (AppCompatTextView) Utils.findRequiredViewAsType(source, C0521R.id.googleConnectIndicatorTextView, "field 'googleConnectIndicatorTextView'", AppCompatTextView.class);
        target.photoImageView = (RoundedImageView) Utils.findRequiredViewAsType(source, C0521R.id.photoImageView, "field 'photoImageView'", RoundedImageView.class);
        View view = Utils.findRequiredView(source, C0521R.id.setDisplayNameButton, "field 'setDisplayNameButton' and method 'onClickSetDisplayName'");
        target.setDisplayNameButton = (AppCompatImageButton) Utils.castView(view, C0521R.id.setDisplayNameButton, "field 'setDisplayNameButton'", AppCompatImageButton.class);
        this.view2131820839 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickSetDisplayName(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.connectFacebookButton, "field 'connectFacebookButton' and method 'onClickConnectFacebook'");
        target.connectFacebookButton = (FloatingActionButton) Utils.castView(view, C0521R.id.connectFacebookButton, "field 'connectFacebookButton'", FloatingActionButton.class);
        this.view2131820845 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickConnectFacebook(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.connectTwitterButton, "field 'connectTwitterButton' and method 'onClickConnectTwitter'");
        target.connectTwitterButton = (FloatingActionButton) Utils.castView(view, C0521R.id.connectTwitterButton, "field 'connectTwitterButton'", FloatingActionButton.class);
        this.view2131820847 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickConnectTwitter(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.connectGoogleButton, "field 'connectGoogleButton' and method 'onClickConnectGoogle'");
        target.connectGoogleButton = (FloatingActionButton) Utils.castView(view, C0521R.id.connectGoogleButton, "field 'connectGoogleButton'", FloatingActionButton.class);
        this.view2131820849 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickConnectGoogle(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.displayNameTextView = null;
        target.gameCountTextView = null;
        target.winCountTextView = null;
        target.loseCountTextView = null;
        target.drawCountTextView = null;
        target.ongoingCountTextView = null;
        target.facebookConnectIndicatorTextView = null;
        target.twitterConnectIndicatorTextView = null;
        target.googleConnectIndicatorTextView = null;
        target.photoImageView = null;
        target.setDisplayNameButton = null;
        target.connectFacebookButton = null;
        target.connectTwitterButton = null;
        target.connectGoogleButton = null;
        this.view2131820839.setOnClickListener(null);
        this.view2131820839 = null;
        this.view2131820845.setOnClickListener(null);
        this.view2131820845 = null;
        this.view2131820847.setOnClickListener(null);
        this.view2131820847 = null;
        this.view2131820849.setOnClickListener(null);
        this.view2131820849 = null;
        this.target = null;
    }
}
