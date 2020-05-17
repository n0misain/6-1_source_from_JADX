package com.cranevalley.dontendword.features.main.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class HomeFragment_ViewBinding<T extends HomeFragment> implements Unbinder {
    protected T target;
    private View view2131820750;
    private View view2131820751;
    private View view2131820752;
    private View view2131820753;
    private View view2131820754;
    private View view2131820755;
    private View view2131820756;

    @UiThread
    public HomeFragment_ViewBinding(final T target, View source) {
        this.target = target;
        View view = Utils.findRequiredView(source, C0521R.id.singlePlayerButton, "method 'onClickSinglePlayer'");
        this.view2131820750 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickSinglePlayer(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.multiplayerButton, "method 'onClickMultiplayer'");
        this.view2131820751 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickMultiplayer(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.playerProfileButton, "method 'onClickPlayerProfile'");
        this.view2131820752 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickPlayerProfile(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.settingsButton, "method 'onClickSettings'");
        this.view2131820753 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickSettings(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.shareButton, "method 'onClickShare'");
        this.view2131820754 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickShare(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.rulesButton, "method 'onClickRules'");
        this.view2131820755 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickRules(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.aboutButton, "method 'onClickAbout'");
        this.view2131820756 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickAbout(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.view2131820750.setOnClickListener(null);
        this.view2131820750 = null;
        this.view2131820751.setOnClickListener(null);
        this.view2131820751 = null;
        this.view2131820752.setOnClickListener(null);
        this.view2131820752 = null;
        this.view2131820753.setOnClickListener(null);
        this.view2131820753 = null;
        this.view2131820754.setOnClickListener(null);
        this.view2131820754 = null;
        this.view2131820755.setOnClickListener(null);
        this.view2131820755 = null;
        this.view2131820756.setOnClickListener(null);
        this.view2131820756 = null;
        this.target = null;
    }
}
