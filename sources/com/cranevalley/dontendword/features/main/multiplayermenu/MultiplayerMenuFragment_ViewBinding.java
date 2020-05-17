package com.cranevalley.dontendword.features.main.multiplayermenu;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.cranevalley.dontendword.C0521R;

public class MultiplayerMenuFragment_ViewBinding<T extends MultiplayerMenuFragment> implements Unbinder {
    protected T target;
    private View view2131820807;
    private View view2131820808;

    @UiThread
    public MultiplayerMenuFragment_ViewBinding(final T target, View source) {
        this.target = target;
        target.findRandomProgressBar = (ProgressBar) Utils.findRequiredViewAsType(source, C0521R.id.findRandomProgressBar, "field 'findRandomProgressBar'", ProgressBar.class);
        target.playerGamesTabLayout = (TabLayout) Utils.findRequiredViewAsType(source, C0521R.id.playerGamesTabLayout, "field 'playerGamesTabLayout'", TabLayout.class);
        target.playerGamesViewPager = (ViewPager) Utils.findRequiredViewAsType(source, C0521R.id.playerGamesViewPager, "field 'playerGamesViewPager'", ViewPager.class);
        View view = Utils.findRequiredView(source, C0521R.id.playRandomLayout, "field 'playRandomLayout' and method 'onClickPlayRandom'");
        target.playRandomLayout = (LinearLayout) Utils.castView(view, C0521R.id.playRandomLayout, "field 'playRandomLayout'", LinearLayout.class);
        this.view2131820807 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickPlayRandom(p0);
            }
        });
        view = Utils.findRequiredView(source, C0521R.id.playFriendLayout, "field 'playFriendLayout' and method 'onClickPlayFriend'");
        target.playFriendLayout = (LinearLayout) Utils.castView(view, C0521R.id.playFriendLayout, "field 'playFriendLayout'", LinearLayout.class);
        this.view2131820808 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target.onClickPlayFriend(p0);
            }
        });
    }

    @CallSuper
    public void unbind() {
        T target = this.target;
        if (target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        target.findRandomProgressBar = null;
        target.playerGamesTabLayout = null;
        target.playerGamesViewPager = null;
        target.playRandomLayout = null;
        target.playFriendLayout = null;
        this.view2131820807.setOnClickListener(null);
        this.view2131820807 = null;
        this.view2131820808.setOnClickListener(null);
        this.view2131820808 = null;
        this.target = null;
    }
}
